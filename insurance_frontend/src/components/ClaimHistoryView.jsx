import { useEffect, useState } from "react"

import Alert from "react-bootstrap/Alert"
import Button from "react-bootstrap/Button"
import ButtonGroup from "react-bootstrap/ButtonGroup"
import Container from "react-bootstrap/Container"
import Table from "react-bootstrap/Table"

import { getClaimHistory } from "../api/claims.js"
import NavigationBar from "./NavigationBar.jsx"
import "../styles/ClaimHistoryView.css"

const filters = ["ALL", "PENDING", "APPROVED", "REJECTED"]

const ClaimHistoryView = () => {
    const [claims, setClaims] = useState([])        // state array of claim objects
    const [filter, setFilter] = useState("ALL")     // state string for status type
    const [loading, setLoading] = useState(true)    // state boolean for flagging loading status
    const [error, setError] = useState("")          // state string for error message

    // on mount,
    useEffect(() => {
        getClaimHistory()                                               // retrieve all existing claims of logged in user
            .then(setClaims)                                            // populates state array with claim objects
            .catch((requestError) => setError(requestError.message))    // if an error is received, set the error message
            .finally(() => setLoading(false))                           // set loading flag to false, regardless of outcome
    }, [])

    // array of claim objects with the status "ALL"
    const visibleClaims = (filter === "ALL")                // if the filter is set to "ALL", visibleClaims will consist of:
        ? claims                                            // all claims without filtering
        : claims.filter((claim) => claim.status === filter) // claims that meet the status filter

    return (
        <>
            <NavigationBar />
            <Container id="claim-history-view">
                <div id="claim-history-header">
                    <div>
                        <h1>Claim history</h1>
                        <p>Review the status of your submitted claims.</p>
                    </div>
                    {/* policy selectors */}
                    <ButtonGroup>
                        {filters.map((filterOption) => (
                            <Button
                                key={filterOption}
                                variant={filter === filterOption ? "primary" : "outline-primary"}
                                onClick={() => setFilter(filterOption)}
                            >
                                {filterOption === "ALL" ? "ALL" : filterOption}
                            </Button>
                        ))}
                    </ButtonGroup>
                </div>

                {error && <Alert variant="danger">{error}</Alert>}
                {loading && <p>Loading claim history...</p>}
                {!loading && !error && claims.length === 0 && (
                    <p>No claims have been submitted yet.</p>
                )}
                {!loading && !error && claims.length > 0 && visibleClaims.length === 0 && (
                    <p>No claims match the selected status.</p>
                )}

                {!loading && !error && visibleClaims.length > 0 && (
                    <Table responsive striped bordered hover>
                        <thead>
                            <tr>
                                <th>Claim ID</th>
                                <th>Policy</th>
                                <th>Date</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {visibleClaims.map((claim) => (
                                <tr key={claim.claimId}>
                                    <td>{claim.claimId}</td>
                                    <td>{claim.policyName}</td>
                                    <td>{claim.claimDate}</td>
                                    <td>{claim.claimAmount}</td>
                                    <td>
                                        <span className={`claim-status claim-status-${claim.status.toLowerCase()}`}>
                                            {claim.status}
                                        </span>
                                    </td>
                                    <td>{claim.description || "-"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )}
            </Container>
        </>
    )
}

export default ClaimHistoryView
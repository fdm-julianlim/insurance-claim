import { useEffect, useState } from "react"

import "../styles/CoverageView.css"

import Container from "react-bootstrap/Container"
import Row from "react-bootstrap/Row"
import Col from "react-bootstrap/Col"
import Button from "react-bootstrap/Button"

import { getClaimHistory, getPolicies } from "../api/claims.js"
import Policies from "../constants/Policies.jsx"
import NavigationBar from "./NavigationBar.jsx"

const CoverageView = () => {
    const [policies, setPolicies] = useState([])
    const [claims, setClaims] = useState([])
    const [selectedPolicy, setSelectedPolicy] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState("")

    useEffect(() => {
        Promise.all([getPolicies(), getClaimHistory()])
        .then(([loadedPolicies, loadedClaims]) => {
            const loadedPolicyNames = new Set(loadedPolicies.map((policy) => policy.name))
            const unavailablePolicies = Policies
                .filter((policyName) => !loadedPolicyNames.has(policyName))
                .map((policyName, index) => ({
                    id: `unavailable-${index}`,
                    name: policyName,
                    active: false,
                    coverage: 0,
                }))

            setPolicies([...loadedPolicies, ...unavailablePolicies])
            setClaims(loadedClaims)
            setSelectedPolicy(loadedPolicies[0] ?? unavailablePolicies[0] ?? null)
        })
        .catch((requestError) => {
        setError(requestError.message)
        })
        .finally(() => {
        setLoading(false)
        })
    }, [])

    const handlePolicyClick = (policy) => {
        setSelectedPolicy(policy);
    }

    const selectedPolicyClaimCount = selectedPolicy
        ? claims.filter((claim) => claim.policyId === selectedPolicy.id).length
        : 0;

    return (
        <>
            <NavigationBar></NavigationBar>
            <Container fluid id="coverage-page">
                <Row>
                    <div id="viewclaims-greeting">
                        <div>
                            <p className="coverage-eyebrow">Policy overview</p>
                            <h1>Welcome, Policyholder Name.</h1>
                            <p className="coverage-intro">Review your active protection and policy limits.</p>
                        </div>
                    </div>
                </Row>
                <Row id="viewclaims-mainview">
                    <Col>
                        <div id="viewclaims-modallist">
                            {loading && <p>Loading policies...</p>}
                            {error && <p>{error}</p>}
                            {!loading && !error && policies.length === 0 && (
                                <p>No policies found.</p>
                            )}
                            <p className="coverage-section-label">Your policies</p>
                            <ul className="policy-list">
                                {policies.map((policy) => (
                                    <Button className="insurance-modal"
                                            key={policy.id}
                                            active={selectedPolicy?.id === policy.id}
                                            onClick={() => handlePolicyClick(policy)}>
                                        <h2>{policy.name}</h2>
                                    </Button>
                                ))}
                            </ul>
                        </div>
                    </Col>
                    <Col>
                        {selectedPolicy && (
                            <div id="viewclaims-description">
                                <div className="policy-detail-heading">
                                    <div>
                                        <p className="coverage-eyebrow">Selected policy</p>
                                        <h2>{selectedPolicy.name}</h2>
                                    </div>
                                    <span className={`coverage-status ${selectedPolicy.active ? "is-covered" : "is-not-covered"}`}>
                                        {selectedPolicy.active ? "Covered" : "Not Covered"}
                                    </span>
                                </div>
                                <div className="policy-details">
                                    {selectedPolicy.active && (
                                        <>
                                            <div>
                                                <span>Coverage limit</span>
                                                <strong>${Number(selectedPolicy.coverage).toLocaleString()}</strong>
                                            </div>
                                            <div>
                                                <span>Claims made</span>
                                                <strong>{selectedPolicyClaimCount}</strong>
                                            </div>
                                        </>
                                    )}
                                    {!selectedPolicy.active && (
                                        <p className="policy-not-covered-message">
                                            You are not covered for this policy. 
                                            Please purchase a {selectedPolicy.name} plan to activate coverage.
                                        </p>
                                    )}
                                </div>
                            </div>
                        )}
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CoverageView
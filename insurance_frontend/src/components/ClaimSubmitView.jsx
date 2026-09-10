import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

import Alert from "react-bootstrap/Alert"
import Button from "react-bootstrap/Button"
import Container from "react-bootstrap/Container"
import Form from "react-bootstrap/Form"

import { getPolicies, submitClaim } from "../api/claims.js"
import NavigationBar from "./NavigationBar.jsx"
import "../styles/ClaimSubmitView.css"

const initialForm = {
    policyId: "",
    claimDate: "",
    claimAmount: "",
    description: "",
}

const ClaimSubmitView = () => {
    const navigate = useNavigate()
    const [policies, setPolicies] = useState([])                    // state array of policy objects
    const [form, setForm] = useState(initialForm)                   // state object with form properties
    const [loadingPolicies, setLoadingPolicies] = useState(true)
    const [submitting, setSubmitting] = useState(false)
    const [error, setError] = useState("")                          
    const [success, setSuccess] = useState("")

    // on mount,
    useEffect(() => {
        getPolicies()                                                   // retrieve all policies of logged in user
            .then(setPolicies)                                          // populates state array with policies
            .catch((requestError) => setError(requestError.message))    // if error is received, set error message
            .finally(() => setLoadingPolicies(false))                   // set loading to false, regardless of outcome
    }, [])

    const handleChange = (event) => {
        const { name, value } = event.target    // destructures the key value pairs from the event target

        // maintains the values of the untouched fields, and updates value of the modified field
        setForm((currentForm) => ({
            ...currentForm,
            [name]: value,
        }))
        // clear previous error/success messages
        setError("")
        setSuccess("")
    }

    const handleSubmit = async (event) => {
        event.preventDefault()
        setError("")
        setSuccess("")

        if (!form.policyId || !form.claimDate || !form.claimAmount) {
            setError("Select a policy and complete the required fields.")
            return
        }

        setSubmitting(true)

        try {
            await submitClaim({
                policyId: Number(form.policyId),
                claimDate: form.claimDate,
                claimAmount: Number(form.claimAmount),
                description: form.description,
            })
            setForm(initialForm)
            setSuccess("Your claim was submitted and is now pending review.")
        } catch (requestError) {
            setError(requestError.message)
        } finally {
            setSubmitting(false)
        }
    }

    return (
        <>
            <NavigationBar />
            <Container id="claim-submit-view">
                <div id="claim-submit-panel">
                    <h1>Submit a claim</h1>
                    <p>Provide the details of your claim for review.</p>

                    {error && <Alert variant="danger">{error}</Alert>}
                    {success && <Alert variant="success">{success}</Alert>}

                    <Form onSubmit={handleSubmit}>
                        <Form.Group controlId="claim-policy">
                            <Form.Label>Policy</Form.Label>
                            <Form.Select
                                name="policyId"
                                value={form.policyId}
                                onChange={handleChange}
                                disabled={loadingPolicies || submitting}
                            >
                                <option value="">
                                    {loadingPolicies
                                        ? "Loading policies..."
                                        : "Select a policy"}
                                </option>
                                {policies.map((policy) => (
                                    <option key={policy.id} value={policy.id}>
                                        {policy.name}
                                    </option>
                                ))}
                            </Form.Select>
                        </Form.Group>

                        <Form.Group controlId="claim-date">
                            <Form.Label>Claim date</Form.Label>
                            <Form.Control
                                type="date"
                                name="claimDate"
                                value={form.claimDate}
                                onChange={handleChange}
                                disabled={submitting}
                            />
                        </Form.Group>

                        <Form.Group controlId="claim-amount">
                            <Form.Label>Claim amount</Form.Label>
                            <Form.Control
                                type="number"
                                name="claimAmount"
                                value={form.claimAmount}
                                onChange={handleChange}
                                min="0.01"
                                step="0.01"
                                disabled={submitting}
                            />
                        </Form.Group>

                        <Form.Group controlId="claim-description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                as="textarea"
                                name="description"
                                value={form.description}
                                onChange={handleChange}
                                rows={5}
                                maxLength={2000}
                                disabled={submitting}
                            />
                        </Form.Group>

                        <div className="claim-submit-actions">
                            <Button
                                type="button"
                                variant="outline-secondary"
                                onClick={() => navigate("/")}
                                disabled={submitting}
                            >
                                Cancel
                            </Button>
                            <Button
                                type="submit"
                                variant="primary"
                                disabled={loadingPolicies || submitting || policies.length === 0}
                            >
                                {submitting ? "Submitting..." : "Submit claim"}
                            </Button>
                        </div>
                    </Form>
                </div>
            </Container>
        </>
    )
}

export default ClaimSubmitView
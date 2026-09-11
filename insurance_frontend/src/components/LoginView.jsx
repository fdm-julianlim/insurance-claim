import { useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"

import Alert from "react-bootstrap/Alert"
import Button from "react-bootstrap/Button"
import Container from "react-bootstrap/Container"
import Form from "react-bootstrap/Form"

import { login } from "../api/auth.js"
import fdm_logo from "../images/fdm_logo.png"
import "../styles/LoginView.css"

const LoginView = () => {
    const navigate = useNavigate()
    const location = useLocation()
    const [form, setForm] = useState({username: "", password: ""})
    const [error, setError] = useState("")
    const [submitting, setSubmitting] = useState(false)

    const handleChange = (event) => {
        const {name, value} = event.target
        setForm((currentForm) => ({...currentForm, [name]: value}))
        setError("")
    }

    const handleSubmit = async (event) => {
        event.preventDefault()
        setError("")
        setSubmitting(true)

        try {
            await login(form.username, form.password)
            const destination = location.state?.from?.pathname ?? "/"
            navigate(destination, {replace: true})
        } catch (requestError) {
            setError(requestError.message)
        } finally {
            setSubmitting(false)
        }
    }

    return (
        <main id="login-page">
            <Container>
                <div id="login-panel">
                    <p className="login-eyebrow">Insurance portal</p>
                    <img className="login-logo" src={fdm_logo} alt="FDM" />
                    <p className="login-intro">Sign in to review your protection and manage claims.</p>

                    {error && <Alert variant="danger">{error}</Alert>}

                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="login-username">
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                name="username"
                                value={form.username}
                                onChange={handleChange}
                                autoComplete="username"
                                required
                                autoFocus
                            />
                        </Form.Group>

                        <Form.Group className="mb-4" controlId="login-password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type="password"
                                name="password"
                                value={form.password}
                                onChange={handleChange}
                                autoComplete="current-password"
                                required
                            />
                        </Form.Group>

                        <Button type="submit" disabled={submitting}>
                            {submitting ? "Signing in..." : "Sign in"}
                        </Button>
                    </Form>
                </div>
            </Container>
        </main>
    )
}

export default LoginView
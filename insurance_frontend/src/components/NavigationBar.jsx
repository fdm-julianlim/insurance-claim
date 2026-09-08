import { Container, Row, Col, Button, Nav, Navbar } from "react-bootstrap"
import fdm_logo from "../images/fdm_logo.png"

import "../styles/NavigationBar.css"
import policies from "../constants/Policies.jsx"
import { useState } from "react"

const NavigationBar = () => {
    const [selectedPolicy, setSelectedPolicy] = useState(policies[0])

    return (
        <>
            <Navbar>
                <Container fluid id="viewclaims-navbar">
                    <Navbar.Brand href="#home">
                        <img id="fdm-logo" src={fdm_logo} alt="FDM"></img>
                    </Navbar.Brand>
                    <div id="navbar-actions">
                            <Nav.Link href="#submit-claim">Submit Claim</Nav.Link>
                            <Nav.Link href="#home">Logout</Nav.Link>
                        </div>
                </Container>
            </Navbar>

            <Container fluid>
                <Row>
                    <div id="viewclaims-greeting">
                        <h1>Welcome, Policyholder Name.</h1>
                    </div>
                </Row>
                <Row id="viewclaims-mainview">
                    <Col>
                        <div id="viewclaims-modallist">
                            <ul>
                                {policies.map(policy => {
                                    return (
                                        <Button id="insurance-modal" variant="success">
                                            <h2 key={policy}>{policy}</h2>
                                        </Button>
                                    )
                                }
                                )}
                            </ul>
                        </div>
                    </Col>
                    <Col>
                        <div id="viewclaims-description">
                            <h2>Your coverage protection on: {selectedPolicy}</h2>
                            <br/>
                            <h2>You are covered.</h2>
                        </div>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default NavigationBar
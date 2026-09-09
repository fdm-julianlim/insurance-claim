import { useState } from "react"

import "../styles/CoverageView.css"

import Container from "react-bootstrap/Container"
import Row from "react-bootstrap/Row"
import Col from "react-bootstrap/Col"
import Button from "react-bootstrap/Button"

import policies from "../constants/Policies.jsx"
import NavigationBar from "./NavigationBar.jsx"

const CoverageView = () => {
    const [selectedPolicy, setSelectedPolicy] = useState(policies[0])

    const handlePolicyClick = (policy) => {
        setSelectedPolicy(policy);
    }
    
    return (
        <>
            <NavigationBar></NavigationBar>
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
                                        <Button className="insurance-modal"
                                                key={policy}
                                                active={selectedPolicy === policy}
                                                onClick={() => handlePolicyClick(policy)}>
                                            <h2>{policy}</h2>
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

export default CoverageView
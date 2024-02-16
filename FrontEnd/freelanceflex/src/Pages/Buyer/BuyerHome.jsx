// Import necessary libraries and components
import React from 'react';
import Buyernavbar from '../../Components/Buyernavbar';
import { Container, Row, Col } from 'react-bootstrap';

// FreelancerHome component
export function FreelancerHome() {
  return (
    <>
      {/* Include the BuyerNavbar component */}
      <Buyernavbar />

      {/* Main content */}
    <Container className="mt-3">
      <Row>
        <Col>
          {/* Container with welcoming message style */}
          <div className="bg-light p-5 rounded">
            <h1 className="display-4">Welcome to the Freelance Marketplace!</h1>
            <p className="lead">
              Explore a wide range of freelancers and find the perfect talent for your projects.
            </p>
          </div>
        </Col>
      </Row>
      
      {/* Additional sections or components can be added here */}
    </Container>
    </>
  );
}

export default FreelancerHome;

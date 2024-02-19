import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Form, Button } from 'react-bootstrap';
import './Buyerviewprofile.css'; // Assuming you have a CSS file for Freelancer view profile styles
import Adminnavbar from '../../Components/Adminnavbar';
import { findFreelancerByEmail, blockFreelancer, deleteFreelancer, getAllFreelancerOrders } from '../../Services/Admin'; // Import necessary functions from services

export function FreelancerViewProfile() {
  const [freelancerDetails, setFreelancerDetails] = useState(null);
  const [searchEmail, setSearchEmail] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null); // New state for error handling
  const [orders, setOrders] = useState([]);
  const [showOrders, setShowOrders] = useState(false); // State to track if orders are shown

  const handleSearch = async () => {
    setLoading(true);
    setError(null); // Reset error state
    try {
      const { data, status } = await findFreelancerByEmail(searchEmail);
      if (status !== 200) {
        throw new Error('Failed to fetch freelancer details');
      }
      setFreelancerDetails(data);
    } catch (error) {
      console.error('Error fetching freelancer details:', error);
      setError('Failed to fetch freelancer details. Please try again.'); // Set error message
    } finally {
      setLoading(false);
    }
  };

  const handleBlockFreelancer = async () => {
    try {
      const { status } = await blockFreelancer(freelancerDetails.id);
      if (status === 200) {
        console.log('Freelancer blocked successfully');
        setFreelancerDetails(null); // Clear freelancer details after blocking
        alert('Freelancer blocked successfully');
      } else {
        throw new Error('Failed to block Freelancer');
      }
    } catch (error) {
      console.error('Error blocking Freelancer:', error);
      alert('Failed to block Freelancer. Please try again.');
    }
  };

  const handleDeleteFreelancer = async () => {
    try {
      const { status } = await deleteFreelancer(freelancerDetails.id);
      if (status === 200) {
        console.log('Freelancer deleted successfully');
        setFreelancerDetails(null); // Clear freelancer details after deletion
        alert('Freelancer deleted successfully');
      } else {
        throw new Error('Failed to delete Freelancer');
      }
    } catch (error) {
      console.error('Error deleting Freelancer:', error);
      alert('Failed to delete Freelancer. Please try again.');
    }
  };

  useEffect(() => {
    if (freelancerDetails && showOrders) {
      fetchOrders();
    }
  }, [freelancerDetails, showOrders]);

  const fetchOrders = async () => {
    try {
      const ordersResponse = await getAllFreelancerOrders(freelancerDetails.id);
      if (ordersResponse.status === 200) {
        setOrders(ordersResponse.data);
      } else {
        throw new Error('Failed to fetch orders');
      }
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const handleViewOrders = () => {
    setShowOrders(true);
  };

  return (
    <>
      <Adminnavbar />
      <Container className="mt-3">
        <Row>
          <Col>
            <Card className="profile-card">
              <Card.Header as="h5">Freelancer Profile</Card.Header>
              <Card.Body>
                <Form>
                  <Form.Group controlId="formEmail">
                    <Form.Label>Enter Freelancer Email:</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="Enter email"
                      value={searchEmail}
                      onChange={(e) => setSearchEmail(e.target.value)}
                    />
                  </Form.Group>
                  <Button variant="primary" onClick={handleSearch}>
                    Search
                  </Button>
                </Form>
                {loading && <p>Loading...</p>}
                {error && <p className="text-danger">{error}</p>}
                {freelancerDetails && (
                  <div className="profile-details">
                    <strong>Name:</strong> {freelancerDetails.firstName} {freelancerDetails.lastName} <br />
                    <strong>Email:</strong> {freelancerDetails.email} <br />
                    <strong>Contact No:</strong> {freelancerDetails.contactNo} <br />
                    <strong>Description:</strong> {freelancerDetails.description} <br />
                    <Button variant="info" onClick={handleViewOrders}>View Orders</Button>{' '}
                    <Button variant="danger" onClick={handleBlockFreelancer}>Block</Button>{' '}
                    <Button variant="outline-danger" onClick={handleDeleteFreelancer}>Delete</Button>
                  </div>
                )}
                {freelancerDetails === null && !loading && !error && <p>No Freelancer found</p>}
                {showOrders && (
                  <div>
                    <h2>Order List</h2>
                    <ul>
                      {orders.map((order, index) => (
                        <li key={index}>
                          <p>Order ID: {order.id}</p>
                          <p>Amount: {order.amount}</p>
                          <p>Start Date: {order.startDate}</p>
                          <p>Delivery Date: {order.deliveryDate}</p>
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default FreelancerViewProfile;

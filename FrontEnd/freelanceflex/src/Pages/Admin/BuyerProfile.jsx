import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Form, Button } from 'react-bootstrap';
import './Buyerviewprofile.css';
import Adminnavbar from '../../Components/Adminnavbar';
import { findBuyerByEmail, blockBuyer, deleteBuyer, getAllBuyerOrders } from '../../Services/Admin'; // Import necessary functions from services

export function BuyerViewProfile() {
  const [buyerDetails, setBuyerDetails] = useState(null);
  const [searchEmail, setSearchEmail] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null); // New state for error handling
  const [orders, setOrders] = useState([]);
  const [showOrders, setShowOrders] = useState(false); // State to track if orders are shown

  const handleSearch = async () => {
    setLoading(true);
    setError(null);
    try {
      const { data, status } = await findBuyerByEmail(searchEmail);
      if (status !== 200) {
        throw new Error('Failed to fetch buyer details');
      }
      setBuyerDetails(data);
    } catch (error) {
      console.error('Error fetching buyer details:', error);
      setError('Failed to fetch buyer details. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleBlockBuyer = async () => {
    try {
      const { status } = await blockBuyer(buyerDetails.id);
      if (status === 200) {
        console.log('Buyer blocked successfully');
        setBuyerDetails(null);
        alert('Buyer blocked successfully');
      } else {
        throw new Error('Failed to block Buyer');
      }
    } catch (error) {
      console.error('Error blocking Buyer:', error);
      alert('Failed to block Buyer. Please try again.');
    }
  };

  const handleDeleteBuyer = async () => {
    try {
      const { status } = await deleteBuyer(buyerDetails.id);
      if (status === 200) {
        console.log('Buyer deleted successfully');
        setBuyerDetails(null);
        alert('Buyer deleted successfully');
      } else {
        throw new Error('Failed to delete buyer');
      }
    } catch (error) {
      console.error('Error deleting buyer:', error);
      alert('Failed to delete buyer. Please try again.');
    }
  };

  useEffect(() => {
    if (buyerDetails && showOrders) {
      fetchOrders();
    }
  }, [buyerDetails, showOrders]);

  const fetchOrders = async () => {
    try {
      const ordersResponse = await getAllBuyerOrders(buyerDetails.id);
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
              <Card.Header as="h5">Buyer Profile</Card.Header>
              <Card.Body>
                <Form>
                  <Form.Group controlId="formEmail">
                    <Form.Label>Enter Buyer's Email:</Form.Label>
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
                {buyerDetails && (
                  <div className="profile-details">
                    <strong>Name:</strong> {buyerDetails.firstName} {buyerDetails.lastName} <br />
                    <strong>Email:</strong> {buyerDetails.email} <br />
                    <strong>Contact No:</strong> {buyerDetails.contactNo} <br />
                    <strong>Description:</strong> {buyerDetails.description} <br />
                    <Button variant="info" onClick={handleViewOrders}>View Orders</Button>{' '}
                    <Button variant="danger" onClick={handleBlockBuyer}>Block</Button>{' '}
                    <Button variant="outline-danger" onClick={handleDeleteBuyer}>Delete</Button>
                  </div>
                )}
                {buyerDetails === null && !loading && !error && <p>No buyer found</p>}
              </Card.Body>
            </Card>
          </Col>
        </Row>
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
      </Container>
    </>
  );
}

export default BuyerViewProfile;

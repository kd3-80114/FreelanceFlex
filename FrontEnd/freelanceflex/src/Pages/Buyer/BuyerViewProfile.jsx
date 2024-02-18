import { Container, Row, Col, Card } from 'react-bootstrap';
import './Buyerviewprofile.css' 
import Buyernavbar from '../../Components/Buyernavbar';

var currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
export function BuyerViewProfile() {
  return (
    <>
    <Buyernavbar></Buyernavbar>
    <Container className="mt-3">
      <Row>
        <Col>
          <Card className="profile-card">
            <Card.Header as="h5">Buyer Profile</Card.Header>
            <Card.Body>
              <Card.Title className="profile-name">{`${currentUser.firstName} ${currentUser.lastName}`}</Card.Title>
              <Card.Text className="profile-details">
                <strong>Email:</strong> {currentUser.email} <br />
                <strong>Contact No:</strong> {currentUser.contactNo} <br />
                <strong>Description:</strong> {currentUser.description} <br />
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
    </>
  );
}

export default BuyerViewProfile;

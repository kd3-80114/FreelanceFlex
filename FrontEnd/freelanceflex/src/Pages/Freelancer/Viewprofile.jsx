// // export function Viewprofile()
// // {
// //     return(<>
// //         <h1>Viewprofile</h1>
        
// //         </>)
// // }

// export default Viewprofile
import { Container, Row, Col, Card } from 'react-bootstrap';
import './Buyerviewprofile.css' 
import Freelancenavbar, { Freelancernavbar } from '../../Components/Freelancenavbar';

var currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
export function Viewprofile() {
  return (
    <>
    <Freelancenavbar></Freelancenavbar>
    <Container className="mt-3">
      <Row>
        <Col>
          <Card className="profile-card">
            <Card.Header as="h5">Freelancer Profile</Card.Header>
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

export default Viewprofile;

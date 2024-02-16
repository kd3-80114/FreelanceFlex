import { Container, Row, Col, Card } from 'react-bootstrap';
import Buyernavbar from '../../Components/Buyernavbar';

var buyerReviews = JSON.parse(sessionStorage.getItem('BuyerReviews'));
export function Buyerviewreviews() {
  return (
    <>
    <Buyernavbar></Buyernavbar>
    <Container className="mt-3">
      <Row>
        {buyerReviews.map((review, index) => (
          <Col key={index} md={4} className="mb-4">
            <Card>
              <Card.Body>
                <Card.Title>Title : {review.title}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">Rating: {review.rating}</Card.Subtitle>
                <Card.Text>Description : {review.description}</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>

  
    </>
  );
}

export default Buyerviewreviews;
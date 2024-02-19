// import { Container, Row, Col, Card } from 'react-bootstrap';
// import { useLocation, useNavigate } from 'react-router-dom';
// import Buyernavbar from '../../Components/Buyernavbar';
// // const gig = location.state?.gigtoforward;
// const location = useLocation();
// const reviewsToParse = location.state?.reviews;
// var buyerReviews = JSON.parse(reviewsToParse);

// export function Buyerviewreviews() {
//   return (
//     <>
//     <Buyernavbar></Buyernavbar>
//     <Container className="mt-3">
//       <Row>
//         {buyerReviews.map((review, index) => (
//           <Col key={index} md={4} className="mb-4">
//             <Card>
//               <Card.Body>
//                 <Card.Title>Title : {review.title}</Card.Title>
//                 <Card.Subtitle className="mb-2 text-muted">Rating: {review.rating}</Card.Subtitle>
//                 <Card.Text>Description : {review.description}</Card.Text>
//               </Card.Body>
//             </Card>
//           </Col>
//         ))}
//       </Row>
//     </Container>

  
//     </>
//   );
// }

// export default Buyerviewreviews;

import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import { useLocation } from 'react-router-dom';
import Buyernavbar from '../../Components/Buyernavbar';

export function Buyerviewreviews() {
  const location = useLocation();
  const [buyerReviews, setBuyerReviews] = useState([]);

  useEffect(() => {
    // Load reviews when the component mounts
    // const reviewsToParse 
    // const parsedReviews = JSON.parse(reviewsToParse);
    const finalReviews = location.state?.reviews;
    console.log(finalReviews)
    setBuyerReviews(finalReviews);
  }, [location.state?.reviews]);

  return (
    <>
      <Buyernavbar />
      <Container className="mt-3">
        <Row>
          {buyerReviews.map((review, index) => (
            <Col key={index} md={4} className="mb-4">
              <Card>
                <Card.Body>
                  <Card.Title>Title: {review.title}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">Rating: {review.rating}/5</Card.Subtitle>
                  <Card.Text>Description: {review.description}</Card.Text>
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

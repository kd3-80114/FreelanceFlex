// import Freelancernavbar from "../../Components/Freelancenavbar"

// export function Viewgig()
// {
//     return(<>
//      <Freelancernavbar />
        
        
//         </>)
// }
// export default Viewgig 

import React from "react";
import { Card, Button } from "react-bootstrap";

const Viewgigs = ({ title, gigImage, description, price, deliveryTime, category }) => {
  return (
    <Card style={{ width: '18rem' }}>
      {gigImage && <Card.Img variant="top" src={gigImage} />}
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Card.Text>{description}</Card.Text>
        <Card.Text>Price: ${price}</Card.Text>
        <Card.Text>Category: {category}</Card.Text>
        {deliveryTime && <Card.Text>Delivery Time: {deliveryTime}</Card.Text>}
        <Button variant="primary">View Gig</Button>
      </Card.Body>
    </Card>
  );
};

export default Viewgigs;

import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import Freelancernavbar from "../../Components/Freelancenavbar";
import Uploadimage from "../../Services/Uploadimage";
import {viewAllGigsOfFreelancer} from "../../Services/Freelancer";

const ViewGigs = () => {
  const location = useLocation();
  const [gigs, setGigs] = useState([]);
  const [error, setError] = useState(null);
  var user = JSON.parse(sessionStorage['currentUser']);
  var fid = user.id;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await viewAllGigsOfFreelancer(fid);
        setGigs(response.data);
      } catch (error) {
        setError(error);
      }
    };

    fetchData();
  }, []); // Empty dependency array means this effect will run only once when component mounts



  return (
    <>
      <Freelancernavbar />
      <div className="container">
        <h2 className="my-4">View Gigs</h2>
        <div className="row row-cols-1 row-cols-md-3 g-4">
          {gigs.map((gig) => (
            <div className="col" key={gig.id}>
              <div className="card h-100 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">{gig.title}</h5>
                  <p className="card-text">{gig.description}</p>
                </div>
                <ul className="list-group list-group-flush border-0">
                  <li className="list-group-item border-0">Price: ${gig.price}</li>
                  <li className="list-group-item border-0">Category: {gig.category}</li>
                </ul>
                <Uploadimage id={gig.id} fid={fid}></Uploadimage>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default ViewGigs;

import React from 'react';
import GigCard from './GigCard';

const GigList = ({ gigs }) => {
  return (
    <div className="container">
      <div className="row row-cols-1 row-cols-md-3 g-4">
        {gigs.map((gig, index) => (
          <div key={gig.id} className="col">
            <GigCard gig={gig} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default GigList;



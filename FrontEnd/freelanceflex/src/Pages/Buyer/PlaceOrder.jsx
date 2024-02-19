import React, { useEffect, useState } from 'react';
import GigList from './GigList';
import { getAllGigs } from '../../Services/Buyer';
import { toast } from 'react-toastify';
import {Buyernavbar} from '../../Components/Buyernavbar'
import './buyer.css'
const PlaceOrder = () => {
  const [gigs, setGigs] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await getAllGigs();
        console.log(result)
        if (result.status === 200) {
          setGigs(result.data);
        } else {
          toast.warning('Unable to Load Gigs...');
        }
      } catch (error) {
        console.error('Error fetching gigs:', error);
        toast.error('An error occurred while fetching gigs.');
      }
    };

    fetchData();
  }, []);

  return (<>
  <Buyernavbar className="buyernavbar" />
        <div className="app">
        <h1 className="gigs-title title">Gigs</h1>
        
        <GigList gigs={gigs} />
      </div>
      </>       
  );
};

export default PlaceOrder;


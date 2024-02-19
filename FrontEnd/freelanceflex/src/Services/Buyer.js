import axios from 'axios'
import { createError, createUrl } from './utils'

export async function viewBuyerReviews(id) {
    try {
      const url = createUrl('buyer/viewReview/'+ id)
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url,headers)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
  export async function  getAllGigs() {
    try {
      const url = createUrl('buyer/viewAllGigs')
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url,headers)
      
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
  export async function  getFreelacerIdfromGigsId(gigsid) {
    try {
      const url = createUrl(`buyer/findFreelancerId/${gigsid}`)
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url,headers)
      console.log('response'+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
  export async function  placeOrder(buyerid,email,gigid,deliveryTime,price,freelancerid) {
    try {
      const url = createUrl('buyer/placeOrder')
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const body = 
      {
        buyer: {
          id:buyerid,
          email
        },
        gigToOrder: {
          id: gigid,
          deliveryTime,
          price,
          freelancer: {
            id: freelancerid
          }
        }
      }
      const response = await axios.post(url,body,headers)
      console.log('response'+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
  
    
  export async function  getAllBuyerOrders(buyerid) {
    try {
      const url = createUrl(`buyer/viewOrders/${buyerid}`)
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url,headers)
      console.log('response'+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }

  export async function getFreelancerIdFromOrderId(orderId) {
    try {
      const url = createUrl(`buyer/findFreelancerByOrderId/${orderId}`)
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url,headers)
      console.log('response'+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }


  export async function  makeReview(buyerid,freelanceid,title,description,rating) {
    try {
      console.log("in here")
      const url = createUrl(`buyer/review/${freelanceid}/${buyerid}`)
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const body = 
      {
          title,
          description,
          rating
        
      }

      const response = await axios.post(url,body,headers)
      console.log('response'+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
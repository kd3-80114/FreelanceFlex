import axios from 'axios'
import { createError, createUrl } from './utils'

export async function createnewgig(id,title,description,selectedCategory,price) {
    try {
      const url = createUrl('freelancer/createGig')
      const category=selectedCategory
      const body = 
        {
            freelancer: {
              id 
            },
            title,
            description,
            category,
            price 
          }
          
          const headers = {
            headers: {
              Authorization: sessionStorage['Authorization'],
            },
          }
      
      const response = await axios.post(url, body,headers)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
}

  export async function viewAllGigsOfFreelancer(id) 
  {
    try {
    const url = createUrl('freelancer/viewGigs/'+id)

    const headers = {
      headers: {
        Authorization: sessionStorage['Authorization'],
      },
    }

    var response=await axios.get(url,headers)
    return {data:response.data,status:response.status}
  } catch (ex) {
    return createError(ex)
  }
  }


  export async function  getAllFreelancerOrders(fid) {
    try {
      const url = createUrl(`freelancer/viewOrders/${fid}`)
      // /viewOrders/{freelancerId}
      const headers = {
        headers: {
          Authorization: sessionStorage['Authorization'],
        },
      }
      const response = await axios.get(url, headers)
      console.log('response '+response.data)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }

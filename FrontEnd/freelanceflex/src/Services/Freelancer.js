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
      
      const response = await axios.post(url,body,headers)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }
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


import axios from 'axios';
import { createError, createUrl } from './utils';

export async function findBuyerByEmail(email) {
  try {
    const url = createUrl('admin/findBuyerProfile');
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.post(url, null, {
      params: { email }, // Pass email as a request parameter
      headers: headers,
    });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }
}


export async function findFreelancerByEmail(email){
  try {
    const url = createUrl('admin/findFreelancerProfile');
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.post(url, null, {
      params: { email }, // Pass email as a request parameter
      headers: headers,
    });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }

}

export async function blockFreelancer(freelancerId) {
  try {
    const url = createUrl(`admin/blockFreelancer/${freelancerId}`);
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.get(url, { headers });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }
}

export async function blockBuyer(buyerId) {
  try {
    const url = createUrl(`admin/blockBuyer/${buyerId}`);
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.get(url, { headers });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }
}

export async function deleteFreelancer(freelancerId) {
  try {
    const url = createUrl(`admin/deleteFreelancer/${freelancerId}`);
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.delete(url, { headers });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }
}

export async function deleteBuyer(buyerId) {
  try {
    const url = createUrl(`admin/deleteBuyer/${buyerId}`);
    const headers = {
      Authorization: sessionStorage['Authorization'],
    };
    const response = await axios.delete(url, { headers });
    return { data: response.data, status: response.status };
  } catch (ex) {
    return createError(ex);
  }
}


export async function  getAllBuyerOrders(buyerid) {
  try {
    const url = createUrl(`buyer/viewOrders/${buyerid}`);
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

export async function  getAllFreelancerOrders(freelancerid) {
  try {
    const url = createUrl(`freelancer/viewOrders/${freelancerid}`);
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









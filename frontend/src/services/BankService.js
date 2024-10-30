import axios from "axios"

const REST_API_URL = "http://localhost:8081/bank"

export const createAdmin = (admin) => axios.post(REST_API_URL + "/signUp", admin)

export const login = (admin) => axios.post(REST_API_URL + '/signIn', admin)

export const getAllUsers = () => axios.get(REST_API_URL + '/getAll/users',{
    headers: {
        Authorization : 'Bearer ' + localStorage.getItem('token')
    }
})

export const addUser = async(user) => {
    try{
        const response = await axios.post(REST_API_URL + '/addUser',user,{
            headers : {
                Authorization : 'Bearer '+ localStorage.getItem('token')
            }
        })
        return response.data
    }
    catch(error){
        console.error("Error in adding user: ", error)
    }    
}
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/contacts';

export const createContact = async (contactData) => {
  await axios.post(BASE_URL, contactData);
};

export const deleteContact = async (id) => {
  await axios.delete(`${BASE_URL}/${id}`);
};

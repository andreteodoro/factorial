import React, { useState, useEffect } from 'react';
import ContactForm from './components/ContactForm/ContactForm';
import ContactList from './components/ContactList/ContactList';
import { createContact, deleteContact } from './services/api';
import './App.css';

const App = () => {
  const [contacts, setContacts] = useState([]);

  useEffect(() => {
    fetchContacts();
  }, []);

  const fetchContacts = async () => {
    try {
      // eslint-disable-next-line no-undef
      const response = await axios.get(BASE_URL);
      setContacts(response.data);
    } catch (error) {
      console.error('Error fetching contacts:', error);
    }
  };

  const handleContactCreated = () => {
    fetchContacts();
  };

  const handleDeleteContact = async (id) => {
    try {
      await deleteContact(id);
      fetchContacts();
    } catch (error) {
      console.error('Error deleting contact:', error);
    }
  };

  return (
    <div>
      <h1>Contact Management Application</h1>
      <ContactForm onContactCreated={handleContactCreated} />
      <ContactList contacts={contacts} onDeleteContact={handleDeleteContact} />
    </div>
  );
};

export default App;

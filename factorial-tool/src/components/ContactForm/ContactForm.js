import React, { useState } from 'react';
import { createContact } from '../../services/api';

const ContactForm = ({ onContactCreated }) => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createContact({ firstName, lastName, email, phoneNumber });
      onContactCreated();
      // Clear input fields after successful creation
      setFirstName('');
      setLastName('');
      setEmail('');
      setPhoneNumber('');
    } catch (error) {
      console.error('Error creating contact:', error);
    }
  };

  return (
    <div>
      <h2>Create Contact</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="First Name"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Last Name"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="tel"
          placeholder="Phone Number"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        />
        <button type="submit">Create Contact</button>
      </form>
    </div>
  );
};

export default ContactForm;

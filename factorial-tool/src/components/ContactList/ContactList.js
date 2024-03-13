import React from 'react';
import ContactItem from '../ContactItem/ContactItem';

const ContactList = ({ contacts, onDeleteContact }) => {
  return (
    <div>
      <h2>Contacts</h2>
      <ul>
        {contacts.map((contact) => (
          <ContactItem key={contact.id} contact={contact} onDeleteContact={onDeleteContact} />
        ))}
      </ul>
    </div>
  );
};

export default ContactList;

import React from 'react';

const ContactItem = ({ contact, onDeleteContact }) => {
  const handleDelete = () => {
    onDeleteContact(contact.id);
  };

  return (
    <li>
      {contact.firstName} {contact.lastName} - {contact.email} ({contact.phoneNumber})
      <button onClick={handleDelete}>Delete</button>
    </li>
  );
};

export default ContactItem;

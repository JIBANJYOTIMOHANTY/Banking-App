import React, { useState, useEffect } from 'react';
import { getAllUsers } from '../services/BankService';

const HomePage = () => {
  const [users, setUsers] = useState([]);
  const [data, setData] = useState([])
  const [newUser, setNewUser] = useState({ name: '', email: '' });
  const [editUserId, setEditUserId] = useState(null);
  const [editUser, setEditUser] = useState({ name: '', email: '' });
  
  // Mock fetching users (simulate an API call)
  useEffect(() => {
    getAllUsers().then((response) => {
      setData(response.data)
    }).catch(() => 
      console.log("Invalid")
    )
    
  }, []);

  const handleAddUser = () => {
    if (newUser.name && newUser.email) {
      setUsers([...users, { id: users.length + 1, ...newUser }]);
      setNewUser({ name: '', email: '' });
    }
  };

  const handleDeleteUser = (id) => {
    setUsers(users.filter(user => user.id !== id));
  };

  const handleEditUser = (user) => {
    setEditUserId(user.id);
    setEditUser(user);
  };

  const handleUpdateUser = () => {
    setUsers(users.map(user => (user.id === editUserId ? editUser : user)));
    setEditUserId(null);
    setEditUser({ name: '', email: '' });
  };

  return (
    <div className="min-h-screen bg-gray-900 flex justify-center items-center">
      <div className="container mx-auto p-8 bg-gray-800 rounded-lg shadow-lg max-w-4xl">
        <h1 className="text-4xl font-bold text-gray-100 text-center mb-8">User Management</h1>

        {/* Add User Section */}
        <div className="mb-6">
          <h2 className="text-2xl font-semibold text-gray-100 mb-4">Add User</h2>
          <div className="grid sm:grid-cols-2 gap-4">
            <input
              type="text"
              placeholder="Name"
              className="p-3 rounded-lg border-none bg-gray-700 text-gray-200 placeholder-gray-400 focus:ring-2 focus:ring-green-500"
              value={newUser.name}
              onChange={(e) => setNewUser({ ...newUser, name: e.target.value })}
            />
            <input
              type="email"
              placeholder="Email"
              className="p-3 rounded-lg border-none bg-gray-700 text-gray-200 placeholder-gray-400 focus:ring-2 focus:ring-green-500"
              value={newUser.email}
              onChange={(e) => setNewUser({ ...newUser, email: e.target.value })}
            />
          </div>
          <button
            className="mt-5 w-full sm:w-auto bg-green-500 text-white font-bold py-3 px-6 rounded-lg hover:shadow-lg hover:bg-green-600 transition-transform transform hover:scale-105"
            onClick={handleAddUser}
          >
            Add User
          </button>
        </div>

        {/* User List Section (Card-Based Layout) */}
        <div className="mb-8">
          <h2 className="text-2xl font-semibold text-gray-100 mb-4">All Users</h2>
          <div className="grid sm:grid-cols-2 gap-6">
            {(data || users).map((user) => (
              <div key={user.id} className="p-6 bg-gray-700 rounded-lg shadow-md hover:shadow-xl transition-shadow">
                <h3 className="text-xl font-semibold text-gray-100 mb-2">{user.name}</h3>
                <p className="text-gray-400 mb-4">{user.email}</p>
                <div className="flex space-x-4">
                  <button
                    className="bg-yellow-400 text-gray-900 font-bold py-2 px-4 rounded-lg hover:bg-yellow-500 transition"
                    onClick={() => handleEditUser(user)}
                  >
                    Edit
                  </button>
                  <button
                    className="bg-red-500 text-gray-100 font-bold py-2 px-4 rounded-lg hover:bg-red-600 transition"
                    onClick={() => handleDeleteUser(user.id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Edit User Section */}
        {editUserId && (
          <div className="mb-6">
            <h2 className="text-2xl font-semibold text-gray-100 mb-4">Edit User</h2>
            <div className="grid sm:grid-cols-2 gap-4">
              <input
                type="text"
                placeholder="Name"
                className="p-3 rounded-lg border-none bg-gray-700 text-gray-200 placeholder-gray-400 focus:ring-2 focus:ring-blue-500"
                value={editUser.name}
                onChange={(e) => setEditUser({ ...editUser, name: e.target.value })}
              />
              <input
                type="email"
                placeholder="Email"
                className="p-3 rounded-lg border-none bg-gray-700 text-gray-200 placeholder-gray-400 focus:ring-2 focus:ring-blue-500"
                value={editUser.email}
                onChange={(e) => setEditUser({ ...editUser, email: e.target.value })}
              />
            </div>
            <button
              className="mt-5 w-full sm:w-auto bg-blue-500 text-white font-bold py-3 px-6 rounded-lg hover:shadow-lg hover:bg-blue-600 transition-transform transform hover:scale-105"
              onClick={handleUpdateUser}
            >
              Update User
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default HomePage;

# Sentry Notes

## Project Description
Sentry Notes is a project designed to help users manage and track notes with integration into Sentry for error tracking and performance monitoring. This project aims to enhance productivity by providing a platform where notes can be easily created, updated, and monitored for any errors or issues that may arise during usage.

## Features
- **User Authentication**: Secure user login and registration system.
- **Note Management**: Create, read, update, and delete notes seamlessly.
- **Sentry Integration**: Automatically track errors and performance metrics using Sentry.
- **Search Functionality**: Quickly find notes using search filters.
- **User-Friendly Interface**: Intuitive design for optimal user experience.

## Technologies Used
- **Frontend**: React.js for building interactive UIs.
- **Backend**: Node.js with Express for handling API requests.
- **Database**: MongoDB for storing user notes and information.
- **Error Tracking**: Sentry for monitoring application errors and performance.

## Installation Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/mohamed-taha-elmeligy/sentry-notes.git
   cd sentry-notes
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Set up environment variables**: Create a `.env` file in the root directory and add the necessary variables:
   
   ```plaintext
   MONGO_URI=your_mongo_database_uri
   SENTRY_DSN=your_sentry_dsn
   PORT=your_preferred_port
   ```

4. **Run the application**:
   ```bash
   npm start
   ```

## Usage Guide
- To register a new user, navigate to the registration page and fill out the form.
- After creating an account, log in to access the notes dashboard.
- Use the options to add, update, or delete notes as required.
- Monitor errors and performance through the Sentry dashboard linked to your application.

## License
This project is licensed under the MIT License.
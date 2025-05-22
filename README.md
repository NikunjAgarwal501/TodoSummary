# 📝 Todo Summary Assistant

Todo Summary Assistant is a full-stack application that helps users manage todos and generate smart summaries using a Large Language Model (LLM). Summaries can be sent directly to a configured Slack channel for easy sharing and tracking.

---

## 🔧 Setup Instructions

### 🔹 Prerequisites

Ensure the following tools are installed on your system:

- Java 17+
- Node.js (with npm or yarn)
- PostgreSQL (or use [Supabase](https://supabase.com) as a hosted option)

---

## 🔗 Slack Integration Setup

1. **Create a Slack App:**  
   Go to [Slack Apps](https://api.slack.com/apps), create a new app, and choose **Incoming Webhooks**.

2. **Enable Webhooks:**  
   In your app settings, enable **Incoming Webhooks**.

3. **Install the App:**  
   Install the app to your Slack workspace and grant necessary permissions.

4. **Get Webhook URL:**  
   Copy the generated Webhook URL from the app settings.

5. **Configure Your Backend:**  
   Paste the Webhook URL into your backend configuration (`application.properties` or `.env`) and specify the target Slack channel.

---

## 🤖 Cohere Model Integration Setup

Integrate Cohere's NLP model into the backend to generate summaries.

1. **Get Cohere API Key:**  
   Sign up at [https://cohere.ai](https://cohere.ai) and copy your API key from the dashboard.

2. **Use in Backend:**  
   Send structured prompts (e.g., a list of todos) to the Cohere API and handle the returned summary response.

3. **Service Layer Integration:**  
   Create a service class in the backend that communicates with the Cohere API and is called from the controller.

---

## 🏗 Architecture & Design Decisions

The application follows the **MVC (Model-View-Controller)** architecture:

The React frontend sends HTTP requests to the Spring Boot backend. The backend's controller forwards these requests to the service layer, which contains the main business logic. This layer handles LLM and Slack API integration and interacts with the JPA repository to fetch or store data in the Supabase PostgreSQL database. Responses then flow back to the frontend to update the UI.



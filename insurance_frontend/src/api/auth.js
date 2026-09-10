const API_BASE = "/api"
const TOKEN_KEY = "insurance_token"
const USER_KEY = "insurance_user"

export async function login(username, password) {
  const response = await fetch(`${API_BASE}/auth/login`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({username, password}),
  })

  if (!response.ok) {
    throw new Error(response.status === 401
      ? "Invalid username or password."
      : `Login failed with status ${response.status}`)
  }

  const session = await response.json()
  sessionStorage.setItem(TOKEN_KEY, session.token)
  sessionStorage.setItem(USER_KEY, JSON.stringify({name: session.name}))
  return session
}

export function getToken() {
  return sessionStorage.getItem(TOKEN_KEY)
}

export function getStoredUser() {
  const storedUser = sessionStorage.getItem(USER_KEY)
  return storedUser ? JSON.parse(storedUser) : null
}

export function logout() {
  sessionStorage.removeItem(TOKEN_KEY)
  sessionStorage.removeItem(USER_KEY)
}
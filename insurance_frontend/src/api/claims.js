const API_BASE = "/api"

async function request(url, options = {}) {
  const token = sessionStorage.getItem("insurance_token")
  const response = await fetch(
    `${API_BASE}${url}`, {
    headers: {
      "Content-Type": "application/json",
      ...(token ? {Authorization: `Bearer ${token}`} : {}),
      ...options.headers,
    }, ...options,
  })

  if (!response.ok) {
    throw new Error(`Request failed with status ${response.status}`)
  }

  return response.json()
}

export function getPolicies() {
  return request("/me/policies")
}

export function getClaimHistory() {
  return request("/me/claims")
}

export function submitClaim(claim) {
  return request("/me/claims", {
    method: "POST",
    body: JSON.stringify(claim),
  })
}
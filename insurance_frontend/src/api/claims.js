const API_BASE = "/api"
const CLAIMANT_ID = 1

async function request(url, options = {}) {
  const response = await fetch(
    `${API_BASE}${url}`, {
    headers: {"Content-Type": "application/json"}, ...options,
  })

  if (!response.ok) {
    throw new Error(`Request failed with status ${response.status}`)
  }

  return response.json()
}

export function getPolicies() {
  return request(`/claimants/${CLAIMANT_ID}/policies`)
}

export function getClaimHistory() {
  return request(`/claimants/${CLAIMANT_ID}/claims`)
}

export function submitClaim(claim) {
  return request(`/claimants/${CLAIMANT_ID}/claims`, {
    method: "POST",
    body: JSON.stringify(claim),
  })
}
export const fetchMyBadges = () => {
    return axios.get('/badges/me')
}

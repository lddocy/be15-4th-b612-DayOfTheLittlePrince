import api from '@/plugins/axios'

export async function fetchMyBadges() {
    const res = await api.get('/badges/me')
    return res.data.data
}

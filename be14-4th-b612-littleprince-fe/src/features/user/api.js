import api from '@/plugins/axios';

export async function fetchMyBadges() {
    const res = await api.get('/badges/me');
    return res.data.data;
}

export function signup(data) {
    return api.post('/member/signup', data);
}

export function login(data) {
    return api.post('/auth/login', data);
}

export function logout(accessToken) {
    return api.post(
        '/auth/logout',
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

export function refreshMemberToken() {
    return api.post('/auth/reissue');
}

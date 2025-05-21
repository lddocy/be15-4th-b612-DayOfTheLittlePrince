import api from '@/plugins/axios.js';

export function fetchMemberInfo(accessToken) {
    return api.get('/member/info', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

export function fetchExpInfo(accessToken) {
    return api.get('/curexp', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}
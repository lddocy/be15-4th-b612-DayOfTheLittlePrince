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

export function updatePlanetName(accessToken, planetName) {
    return api.patch('member/planet/edit', {
        planetName: planetName
    }, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}
/* 알림 조회 */
export const getNotifications = ({ limit = 30, offset = 0 }) =>
    api.get('/notifications', {
        params: { limit, offset }
    });

/* 읽음 처리*/
export const markNotificationAsRead = (notificationId) => {
    return api.patch(`/notifications/${notificationId}/read`)
}


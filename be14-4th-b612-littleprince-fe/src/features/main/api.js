import api from '@/plugins/axios.js';

/* 사용자 정보 조회 */
export function fetchMemberInfo(accessToken) {
    return api.get('/member/info', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 경험치 조회 */
export function fetchExpInfo(accessToken) {
    return api.get('/curexp', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 행성 이름 수정 */
export function updatePlanetName(accessToken, planetName) {
    return api.patch('member/planet/edit', {
        planetName: planetName
    }, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 선택한 칭호 조회 */
export function getSelectedBadge(accessToken) {
    return api.get('/badges/selected', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 알림 조회 */
export const getNotifications = async () => {
    const response = await api.get('/notifications');
    return response.data;
};

/* 읽음 처리*/
export const markNotificationAsRead = (notificationId) => {
    return api.patch(`/notifications/${notificationId}/read`)
}



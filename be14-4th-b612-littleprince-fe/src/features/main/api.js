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

/* 행성 아이템 조회 */
export function getItems(accessToken) {
    return api.get('/item/list', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 아이템 숨김 처리 */
export function toggleItemHidden(accessToken, itemId) {
    return api.patch(`/item/${itemId}/toggle`, {}, {
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

/* 명언 랜덤 조회 */
export function getFamousQuotes(accessToken) {
    return api.get('/famous-quotes', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

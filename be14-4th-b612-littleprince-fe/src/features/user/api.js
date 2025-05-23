import api from '@/plugins/axios';

/* 내 칭호 조회 */
export async function fetchMyBadges() {
    const res = await api.get('/badges/me');
    return res.data.data;
}

/* 내 칭호 선택*/
export async function selectBadge(badgeId) {
    return api.patch('/badges/select', { badgeId });
}

/* 내 아이템 조회 */
export async function fetchMyItems() {
    const res = await api.get('/item/list');
    return res.data.data.itemList; // ← Postman 응답 기준으로 수정
}

/* 아이템 숨김 , 보임 */
export function toggleItemHidden(itemId) {
    return api.patch(`/item/${itemId}/toggle`);
}

/* 내 경험치 조회*/
export async function fetchMyExp() {
    const res = await api.get('/curexp');
    return res.data.data; // { currentExp, currentLevel, totalExpToNextLevel }
}

/* 달성률 조회 */
export async function fetchTaskCompletionRate() {
    const res = await api.get('/plans/rate');
    return res.data; // { totalRate, monthlyRate }
}

/* 푸시 알림용*/
export function subscribePush(subscription) {
    return api.post('/push/subscribe', subscription);
}

/* 최신날짜 3개씩 경험치 이력 조회 */
export function fetchExpHistory(accessToken) {
    return api.get('/curexp/get', {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
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

export function findPassword(email) {
    return api.get('/auth/find/password', {
        params: {
            email: email,
        },
    });
}

export function deleteMember(accessToken){
    return api.delete('/member/delete',
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        })
}

export function changePassword(data) {
    return api.post('/auth/change/password', data);
}

export function refreshMemberToken() {
    return api.post('/auth/reissue');
}

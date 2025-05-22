import api from "@/plugins/axios.js";

/* 장기 투두 생성 - 유경 */
export function createLongTodo(accessToken) {
    return api.post(
        '/plans/long-todos',
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 프로젝트 삭제 - 승재 */
export function deleteLongTodo(accessToken, projectId) {
    return api.delete(`/plans/projects/${projectId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 프로젝트 하위 체크리스트 생성 - 유경 */
export function createProjectTasks(accessToken, projectId, body) {
    return api.post(
        `/plans/project-tasks/${projectId}/tasks`,
        body,
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 투두 체크 상태 토글 - 유경 */
export function toggleProjectTaskCheck(accessToken, taskId) {
    return api.patch(
        `/plans/project-tasks/${taskId}/check`,
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 프로젝트 하위 체크리스트 항목 삭제 - 유경 */
export function deleteProjectTask(accessToken, taskId) {
    return api.delete(`/plans/project-tasks/${taskId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 투두 생성 - 유경 */
export function createShortTodo(accessToken, body) {
    return api.post(
        `/plans/short-todos`,
        body,
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 단기 투두 삭제 - 유경 */
export function deleteShortTodo(accessToken, taskId) {
    return api.delete(`/plans/short-todos/${taskId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 투두 체크 상태 토글 - 유경 */
export function toggleCheck(accessToken, taskId) {
    return api.patch(
        `/plans/short-todos/${taskId}/check`,
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 플랜 전체 조회 - 승재 */
export function getLongPlan(accessToken){
    return api.get('/plan/long', {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 플랜 목록 조회 - 승재 */
export function getLongList(accessToken, date){
    return api.get(`/plan/long/${date}`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 플랜 상세 조회 - 장기 하위 투두리스트 조회 - 유경 */
export function getLongDetail(accessToken, date, projectId){
    return api.get(`/plan/long/${date}/${projectId}`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 리스트 조회 - 유경 */
export function getShortList(accessToken, date){
    return api.get(`/plan/short/${date}/todo` , {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 플랜 날짜 여부 조회 - 승재 */
export function getShortDates(accessToken){
    return api.get(`/plan/short`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}
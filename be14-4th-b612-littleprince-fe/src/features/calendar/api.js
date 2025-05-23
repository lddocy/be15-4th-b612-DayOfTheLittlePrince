import api from "@/plugins/axios.js";

/* 장기 투두 생성 */
export function createLongTodo(accessToken,body) {
    return api.post(
        '/plans/long-todos',
        body,
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 투두 삭제 */
export function deleteLongTodo(accessToken, projectId) {
    return api.delete(`/plans/projects/${projectId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 프로젝트 하위 체크리스트 생성 */
export function createProjectTasks(accessToken, projectId, tasks) {
    return api.post(
        `/plans/project-tasks/${projectId}/tasks`,
        tasks,
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 투두 체크 상태 토글 */
export function toggleProjectTaskCheck(accessToken, taskId) {
    return api.patch(
        `/plans/project-tasks/${taskId}/check`,
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 투두 체크리스트 항목 삭제 */
export function deleteProjectTask(accessToken, taskId) {
    return api.delete(`/plans/project-tasks/${taskId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 투두 생성 */
export function createShortTodo(accessToken, body) {
    return api.post(
        `/plans/short-todos`,
        body,
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 단기 투두 삭제 */
export function deleteShortTodo(accessToken, taskId) {
    return api.delete(`/plans/short-todos/${taskId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 투두 체크 상태 토글 */
export function toggleCheck(accessToken, taskId) {
    return api.patch(
        `/plans/short-todos/${taskId}/check`,
        {},
        {
            headers: { Authorization: `Bearer ${accessToken}` },
        }
    );
}

/* 장기 플랜 전체 조회 */
export function getLongPlan(accessToken){
    return api.get('/plan/long', {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 플랜 목록 조회 */
export function getLongList(accessToken, date){
    return api.get(`/plan/long/${date}`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 장기 플랜 상세 조회 */
export function getLongDetail(accessToken, date, projectId){
    return api.get(`/plan/long/${date}/${projectId}`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 리스트 조회 */
export function getShortList(accessToken, date){
    return api.get(`/plan/short/${date}/todo` , {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 단기 플랜 날짜 여부 조회 */
export function getShortDates(accessToken){
    return api.get(`/plan/short`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 회고 조회 */
export function getReviewDates(accessToken, date){
    return api.get(`/review/${date}`, {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}

/* 회고 작성 및 수정 */
export function reviewCreateOrUpdate(accessToken, date, reviewContent) {
    return api.post(
        `/plan/review/${date}`,
        { reviewContent },
        {
            headers: {
                Authorization: `Bearer ${accessToken}`,
            },
        }
    )
}

/* ai 조회 */
export function getAiList(accessToken){
    return api.get(`/ai/plan`,  {
        params: {},
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    });
}
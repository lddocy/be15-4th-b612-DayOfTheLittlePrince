import api from '@/plugins/axios'

export const getCurrentExp = () => {
    return api.get('/curexp')
}

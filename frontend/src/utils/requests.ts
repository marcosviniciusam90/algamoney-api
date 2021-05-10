//se existir a variável de ambiente definida usa o valor, caso contrário usa 'http://localhost:8080'
export const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

/* 
No Netlify, variáveis de ambiente com prefixo "REACT_APP_" serão processadas pelo browser e o
JavaScript terá acesso as mesmas
*/
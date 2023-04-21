import sql from 'mssql';
import config from '../config.js';

// const dbSettings = {
// 	user: 'sa',
// 	password: 'Login@W',
// 	server: 'App.takako.vn',
// 	database: 'HANDY_PICKING',
// 	options: {
// 		trustServerCertificate: true,
// 	},
// };

export async function getConnection() {
	try {
		const pool = await sql.connect(config.dbSettings);
		return pool;
	} catch (error) {
		console.error(error);
	}
}

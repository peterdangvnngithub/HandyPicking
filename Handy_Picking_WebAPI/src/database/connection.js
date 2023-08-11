import sql from 'mssql';
import config from '../config.js';

export async function getConnection() {
	try {
		const pool = await sql.connect(config.dbSettings);
		return pool;
	} catch (error) {
		console.error(error);
	}
}

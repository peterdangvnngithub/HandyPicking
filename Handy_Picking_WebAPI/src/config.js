import { config } from 'dotenv';
config();

export default {
	port: 3000,
	dbSettings: {
		user: 'sa',
		password: 'Login@W',
		server: 'App.takako.vn',
		database: 'HANDY_PICKING',
		options: {
			trustServerCertificate: true,
		},
	},
};

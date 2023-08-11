import express from 'express';
import config from './config.js';

import handyPickingMS from './routes/handyPickingMS.routes.js';
import handyPickingDetail from './routes/handyPickingDetail.routes.js';

const app = express();

app.head('/', (req, res) => {
    // Send a 200 OK response to indicate that the API is running
    res.status(200).end();
})

// Settings
app.set('port', config.port || 6000);

// Middleware
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use(handyPickingMS);
app.use(handyPickingDetail);

export default app;

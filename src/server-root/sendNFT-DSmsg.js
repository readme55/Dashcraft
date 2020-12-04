//const Dash = require('./dash.min.js');
const Dash = require('dash');
var fs = require('fs');

const dappIdentityId = 'Be9CKRUEXJHro4uzmX8xdw4E5JqRMao1TSWDCDVfBdvP';
const messageContractId = 'CfHbvNx8ZJfhoizqCDawZK53iqyuJXdqwzQ8eVh58bjE';

var myArgs = process.argv.slice(2);
var filepath = myArgs[0];

const clientOpts = {
	network: 'evonet',
    wallet: {
        mnemonic: 'protect horror stove scrub foster brave air ski song bitter stomach siren',
    },
    apps: {
        messageContract: {
            contractId: 'CfHbvNx8ZJfhoizqCDawZK53iqyuJXdqwzQ8eVh58bjE'
        }
    }
};
console.dir(clientOpts)
var client = new Dash.Client(clientOpts);
console.log('new Dash.Client');


const sendMsgContract = async function () {

    try {
        const platform = client.platform;

        //const account = await client.getWalletAccount();
        // const identityId = (await account).getIdentityIds();

        const identity = await platform.identities.get(dappIdentityId);

        //var contents = fs.readFileSync('./plugins/DashCraftPlugin/readme1.txt', 'utf8');
        console.log(filepath)
        var contents = fs.readFileSync(filepath, 'utf8');

        var docProperties = JSON.parse(contents);
        //console.log(contents);

        const msgDocument = await platform.documents.create(
            'messageContract.message',
            identity,
            docProperties,
        );

        const documentBatch = {
            create: [msgDocument],
            replace: [],
            delete: [],
        }

        console.dir({ documentBatch }, { depth: 5 })

        await platform.documents.broadcast(documentBatch, identity);
        console.log("finish broadcast")


    } catch (e) {
        console.error('Something went wrong:', e);
    } finally {
        console.log("disconnect")
        client.disconnect();
    }
};
sendMsgContract()

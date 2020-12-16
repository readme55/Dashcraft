//const Dash = require('./dash.min.js');
const { fail } = require('assert');
const Dash = require('dash');
let fs = require('fs');

const dappIdentityId = 'Be9CKRUEXJHro4uzmX8xdw4E5JqRMao1TSWDCDVfBdvP';
const messageContractId = 'CfHbvNx8ZJfhoizqCDawZK53iqyuJXdqwzQ8eVh58bjE';

let myArgs = process.argv.slice(2);
let inputUsername = myArgs[0];
let identityId = '';
let loginAuth = false;


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
let client = new Dash.Client(clientOpts);
console.log('new Dash.Client');


const dappLoginAuthRequest = async function () {

    try {
        const platform = client.platform;

        //const account = await client.getWalletAccount();
        // const identityId = (await account).getIdentityIds();

        const identity = await platform.identities.get(dappIdentityId); // dapp identity

        //let contents = fs.readFileSync('./plugins/DashCraftPlugin/readme1.txt', 'utf8');
        console.log("Command line argument given: " + inputUsername)

        // get identity ID for user
        console.log("fetch identity ID from username")
        async function getIdentityId() {

            try {

                const identityIdRecord = await client.platform.names.resolve(inputUsername + ".dash");
                identityId = identityIdRecord.data.records.dashUniqueIdentityId.toString()
                console.log(identityId);

            } catch (e) {
                console.error('Something went wrong:', e);
            } finally {
                client.disconnect()
            }
        }
        await getIdentityId();


        // submit auth request to wallet
        console.log("submit Authentication Request")
        const submitAuthRequest = async function () {

            try {

                // create document
                docProperties = {
                    header: 'Request Document ST',
                    dappname: 'Dashcraft Dapp',
                    reference: inputUsername,
                    status: '0',
                    timestamp: new Date().toUTCString(),
                    STcontract: messageContractId,
                    STdocument: 'message',
                    STcontent: '{ "header" : "Response Login", "dappname" : "Dashcraft Dapp", "reference" : "' + inputUsername + '", "timestamp" : "' + new Date().toUTCString() + '", "STcontract" : "' + messageContractId + '", "STdocument" : "message" }',
                }

                // Create the note document
                const messageDocument = await client.platform.documents.create(
                    'messageContract.message',
                    identity,
                    docProperties,
                );

                const documentBatch = {
                    create: [messageDocument],
                    replace: [],
                    delete: [],
                }

                // Sign and submit the document
                await client.platform.documents.broadcast(documentBatch, identity);
            } catch (e) {
                console.error('Something went wrong:', e);
            } finally {
                console.log("submited Request Document ST for user: " + inputUsername)
                client.disconnect();
            }
        };
        // await submitAuthRequest();
        submitAuthRequest();


        console.log("start polling for Authentication Response")
        async function pollAuthResponse() {
            let recordLocator = "messageContract.message";

            let isHead = false;
            let nStart = 1;

            // 150sec / 1.5sec sleep = 100 (iterations for 2.5 min polling)
            // while (true) {
            for (let i = 0; i < 100; i++) {

                queryString = '{ "startAt" : "' + nStart + '" }';
                queryJson = JSON.parse(queryString);
                console.log("Poll document startAt: " + nStart)
                let documents = await client.platform.documents.get(recordLocator, queryJson);

                // find head document (can only poll 100 documents at once)
                if (isHead == false) {
                    if (documents.length == 0) {
                        console.log("Found head at doc nr " + nStart)
                        isHead = true;
                        await new Promise(r => setTimeout(r, 1500));  // sleep x ms
                    }
                    nStart = nStart + documents.length;
                    continue;
                }

                if (documents.length >= 1 && documents[0].ownerId.toString() == identityId && documents[0].data.reference == inputUsername) {
                    console.log("Received valid Authentication Response")
                    return true;
                }
                await new Promise(r => setTimeout(r, 1500));  // sleep x ms
                if (documents.length >= 1) nStart++;
            }
            return false;

        }
        let response = await pollAuthResponse();
        console.log("response: " + response)
        if (response) {
            loginAuth = true;
        }

    } catch (e) {
        console.error('Something went wrong:', e);
    } finally {
        console.log("disconnect")
        client.disconnect();
        if (!loginAuth) {
            return process.exit(1)
        }
    }
};
dappLoginAuthRequest()




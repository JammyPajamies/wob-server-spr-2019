/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import shared.db.ConvergeEcosystemDAO;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeEcosystems;
import shared.util.Log;

/**
 *
 * @author justinacotter
 */
public class RequestConvergeEcosystems extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    	Log.consoleln("Parsing RequestConvergeEcosystems");
    }

    @Override
    public void process() throws Exception {
        Log.consoleln("Processing RequestConvergeEcosystems");
        ResponseConvergeEcosystems response = new ResponseConvergeEcosystems();
        response.setConvergeEcosystems(ConvergeEcosystemDAO.getConvergeEcosystems());
        client.add(response);
    }
}

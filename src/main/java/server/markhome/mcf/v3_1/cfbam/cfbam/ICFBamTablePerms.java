// Description: Java 25 CFBam Table Permissions Interface.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;

public interface ICFBamTablePerms
extends ICFSecTablePerms,
	ICFIntTablePerms
{
	/**
	 *	Is the session allowed to create Atom instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateAtom( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Atom instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadAtom( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Atom instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateAtom( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Atom instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteAtom( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BlobCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBlobCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BlobCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBlobCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BlobCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBlobCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BlobCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBlobCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BlobDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBlobDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BlobDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBlobDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BlobDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBlobDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BlobDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBlobDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BlobType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBlobType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BlobType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBlobType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BlobType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBlobType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BlobType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBlobType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BoolCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBoolCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BoolCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBoolCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BoolCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBoolCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BoolCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBoolCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BoolDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBoolDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BoolDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBoolDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BoolDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBoolDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BoolDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBoolDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create BoolType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateBoolType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read BoolType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadBoolType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update BoolType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateBoolType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete BoolType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteBoolType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Chain instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateChain( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Chain instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadChain( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Chain instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateChain( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Chain instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteChain( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ClearDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateClearDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ClearDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadClearDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ClearDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateClearDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ClearDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteClearDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ClearSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateClearSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ClearSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadClearSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ClearSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateClearSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ClearSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteClearSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ClearSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateClearSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ClearSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadClearSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ClearSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateClearSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ClearSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteClearSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ClearSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateClearSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ClearSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadClearSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ClearSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateClearSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ClearSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteClearSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ClearTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateClearTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ClearTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadClearTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ClearTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateClearTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ClearTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteClearTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash128Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash128Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash128Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash128Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash128Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash128Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash128Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash128Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash128Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash128Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash128Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash128Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash128Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash128Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash128Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash128Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash128Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash128Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash128Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash128Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash128Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash128Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash128Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash128Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash128Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash128Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash128Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash128Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash128Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash128Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash128Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash128Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash160Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash160Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash160Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash160Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash160Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash160Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash160Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash160Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash160Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash160Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash160Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash160Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash160Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash160Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash160Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash160Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash160Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash160Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash160Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash160Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash160Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash160Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash160Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash160Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash160Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash160Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash160Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash160Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash160Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash160Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash160Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash160Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash224Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash224Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash224Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash224Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash224Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash224Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash224Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash224Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash224Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash224Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash224Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash224Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash224Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash224Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash224Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash224Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash224Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash224Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash224Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash224Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash224Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash224Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash224Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash224Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash224Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash224Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash224Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash224Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash224Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash224Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash224Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash224Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash256Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash256Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash256Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash256Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash256Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash256Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash256Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash256Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash256Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash256Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash256Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash256Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash256Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash256Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash256Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash256Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash256Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash256Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash256Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash256Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash256Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash256Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash256Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash256Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash256Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash256Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash256Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash256Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash256Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash256Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash256Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash256Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash384Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash384Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash384Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash384Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash384Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash384Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash384Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash384Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash384Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash384Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash384Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash384Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash384Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash384Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash384Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash384Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash384Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash384Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash384Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash384Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash384Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash384Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash384Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash384Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash384Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash384Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash384Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash384Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash384Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash384Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash384Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash384Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash512Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash512Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash512Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash512Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash512Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash512Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash512Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash512Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash512Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash512Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash512Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash512Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash512Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash512Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash512Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash512Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash512Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash512Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash512Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash512Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash512Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash512Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash512Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash512Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DbKeyHash512Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDbKeyHash512Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DbKeyHash512Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDbKeyHash512Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DbKeyHash512Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDbKeyHash512Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DbKeyHash512Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDbKeyHash512Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DelDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDelDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DelDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDelDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DelDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDelDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DelDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDelDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DelSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDelSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DelSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDelSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DelSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDelSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DelSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDelSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DelSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDelSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DelSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDelSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DelSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDelSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DelSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDelSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DelSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDelSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DelSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDelSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DelSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDelSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DelSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDelSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DelTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDelTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DelTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDelTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DelTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDelTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DelTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDelTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DoubleCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDoubleCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DoubleCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDoubleCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DoubleCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDoubleCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DoubleCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDoubleCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DoubleDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDoubleDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DoubleDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDoubleDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DoubleDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDoubleDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DoubleDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDoubleDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create DoubleType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateDoubleType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read DoubleType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadDoubleType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update DoubleType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateDoubleType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete DoubleType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteDoubleType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create EnumDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateEnumDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read EnumDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadEnumDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update EnumDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateEnumDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete EnumDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteEnumDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create EnumTag instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateEnumTag( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read EnumTag instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadEnumTag( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update EnumTag instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateEnumTag( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete EnumTag instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteEnumTag( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create EnumType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateEnumType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read EnumType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadEnumType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update EnumType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateEnumType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete EnumType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteEnumType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create FloatCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateFloatCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read FloatCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadFloatCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update FloatCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateFloatCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete FloatCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteFloatCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create FloatDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateFloatDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read FloatDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadFloatDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update FloatDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateFloatDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete FloatDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteFloatDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create FloatType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateFloatType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read FloatType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadFloatType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update FloatType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateFloatType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete FloatType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteFloatType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Id16Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateId16Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Id16Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadId16Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Id16Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateId16Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Id16Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteId16Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Id32Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateId32Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Id32Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadId32Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Id32Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateId32Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Id32Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteId32Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Id64Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateId64Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Id64Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadId64Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Id64Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateId64Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Id64Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteId64Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Index instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateIndex( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Index instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadIndex( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Index instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateIndex( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Index instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteIndex( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create IndexCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateIndexCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read IndexCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadIndexCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update IndexCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateIndexCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete IndexCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteIndexCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Int64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Int64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Int64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Int64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokensCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokensCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokensCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokensCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokensCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokensCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokensCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokensCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokensDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokensDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokensDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokensDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokensDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokensDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokensDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokensDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NmTokensType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNmTokensType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NmTokensType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNmTokensType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NmTokensType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNmTokensType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NmTokensType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNmTokensType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NumberCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNumberCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NumberCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNumberCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NumberCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNumberCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NumberCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNumberCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NumberDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNumberDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NumberDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNumberDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NumberDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNumberDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NumberDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNumberDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create NumberType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateNumberType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read NumberType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadNumberType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update NumberType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateNumberType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete NumberType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteNumberType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Param instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateParam( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Param instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadParam( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Param instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateParam( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Param instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteParam( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create PopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreatePopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read PopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadPopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update PopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdatePopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete PopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeletePopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create PopSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreatePopSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read PopSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadPopSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update PopSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdatePopSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete PopSubDep1 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeletePopSubDep1( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create PopSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreatePopSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read PopSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadPopSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update PopSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdatePopSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete PopSubDep2 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeletePopSubDep2( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create PopSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreatePopSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read PopSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadPopSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update PopSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdatePopSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete PopSubDep3 instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeletePopSubDep3( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create PopTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreatePopTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read PopTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadPopTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update PopTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdatePopTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete PopTopDep instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeletePopTopDep( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Relation instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateRelation( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Relation instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadRelation( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Relation instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateRelation( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Relation instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteRelation( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create RelationCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateRelationCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read RelationCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadRelationCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update RelationCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateRelationCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete RelationCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteRelationCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SchemaDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSchemaDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SchemaDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSchemaDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SchemaDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSchemaDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SchemaDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSchemaDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SchemaRef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSchemaRef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SchemaRef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSchemaRef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SchemaRef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSchemaRef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SchemaRef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSchemaRef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Scope instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateScope( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Scope instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadScope( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Scope instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateScope( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Scope instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteScope( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ServerListFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateServerListFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ServerListFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadServerListFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ServerListFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateServerListFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ServerListFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteServerListFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ServerMethod instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateServerMethod( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ServerMethod instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadServerMethod( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ServerMethod instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateServerMethod( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ServerMethod instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteServerMethod( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ServerObjFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateServerObjFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ServerObjFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadServerObjFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ServerObjFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateServerObjFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ServerObjFunc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteServerObjFunc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ServerProc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateServerProc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ServerProc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadServerProc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ServerProc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateServerProc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ServerProc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteServerProc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create StringCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateStringCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read StringCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadStringCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update StringCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateStringCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete StringCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteStringCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create StringDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateStringDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read StringDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadStringDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update StringDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateStringDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete StringDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteStringDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create StringType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateStringType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read StringType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadStringType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update StringType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateStringType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete StringType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteStringType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZDateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZDateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZDateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZDateCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZDateCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZDateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZDateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZDateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZDateDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZDateDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZDateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZDateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZDateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZDateType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZDateType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TZTimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTZTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TZTimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTZTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TZTimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTZTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TZTimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTZTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Table instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTable( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Table instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTable( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Table instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTable( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Table instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTable( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TableCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTableCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TableCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTableCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TableCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTableCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TableCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTableCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TextCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTextCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TextCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTextCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TextCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTextCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TextCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTextCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TextDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTextDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TextDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTextDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TextDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTextDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TextDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTextDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TextType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTextType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TextType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTextType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TextType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTextType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TextType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTextType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimeCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimeCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimeDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimeDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimeType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimeType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimestampCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimestampCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimestampDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimestampDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TimestampType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTimestampType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TokenCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTokenCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TokenDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTokenDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TokenType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTokenType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt16Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt16Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt16Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt16Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt16Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt16Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt32Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt32Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt32Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt32Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt32Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt32Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt64Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt64Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt64Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt64Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UInt64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UInt64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UInt64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UInt64Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUInt64Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Uuid6Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuid6Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Uuid6Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuid6Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Uuid6Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuid6Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Uuid6Col instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuid6Col( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Uuid6Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuid6Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Uuid6Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuid6Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Uuid6Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuid6Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Uuid6Def instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuid6Def( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Uuid6Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuid6Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Uuid6Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuid6Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Uuid6Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuid6Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Uuid6Gen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuid6Gen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Uuid6Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuid6Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Uuid6Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuid6Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Uuid6Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuid6Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Uuid6Type instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuid6Type( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UuidCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuidCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UuidCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuidCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UuidCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuidCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UuidCol instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuidCol( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UuidDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuidDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UuidDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuidDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UuidDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuidDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UuidDef instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuidDef( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UuidGen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuidGen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UuidGen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuidGen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UuidGen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuidGen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UuidGen instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuidGen( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create UuidType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateUuidType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read UuidType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadUuidType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update UuidType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateUuidType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete UuidType instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteUuidType( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Value instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateValue( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Value instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadValue( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Value instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateValue( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Value instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteValue( CFSecAuthorization Authorization );
}

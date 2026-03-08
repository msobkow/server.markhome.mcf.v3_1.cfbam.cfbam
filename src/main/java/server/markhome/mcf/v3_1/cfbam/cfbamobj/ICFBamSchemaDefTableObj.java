// Description: Java 25 Table Object interface for CFBam.

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

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamSchemaDefTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new SchemaDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamSchemaDefObj newInstance();

	/**
	 *	Instantiate a new SchemaDef edition of the specified SchemaDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamSchemaDefEditObj newEditInstance( ICFBamSchemaDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaDefObj realiseSchemaDef( ICFBamSchemaDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaDefObj createSchemaDef( ICFBamSchemaDefObj Obj );

	/**
	 *	Read a SchemaDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaDefObj readSchemaDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SchemaDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaDefObj readSchemaDef( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamSchemaDefObj readCachedSchemaDef( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSchemaDef( ICFBamSchemaDefObj obj );

	void deepDisposeSchemaDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaDefObj lockSchemaDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SchemaDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaDefObj> readAllSchemaDef();

	/**
	 *	Return a sorted map of all the SchemaDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaDefObj> readAllSchemaDef( boolean forceRead );

	List<ICFBamSchemaDefObj> readCachedAllSchemaDef();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate CTenantIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate CTenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate CTenantIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate CTenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate MinorVersionIdx key.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate MinorVersionIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate MinorVersionIdx key.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate MinorVersionIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId,
		boolean forceRead );

	/**
	 *	Get the CFBamSchemaDefObj instance for the unique UNameIdx key.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaDefObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByUNameIdx(CFLibDbKeyHash256 MinorVersionId,
		String Name );

	/**
	 *	Get the CFBamSchemaDefObj instance for the unique UNameIdx key.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaDefObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByUNameIdx(CFLibDbKeyHash256 MinorVersionId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate AuthEMailIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate AuthEMailIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate AuthEMailIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate AuthEMailIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate ProjectURLIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate ProjectURLIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	/**
	 *	Get the map of CFBamSchemaDefObj instances sorted by their primary keys for the duplicate ProjectURLIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaDefObj cached instances sorted by their primary keys for the duplicate ProjectURLIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaDefObj> readSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL,
		boolean forceRead );

	/**
	 *	Get the CFBamSchemaDefObj instance for the unique PubURIIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaDefObj cached instance for the unique PubURIIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByPubURIIdx(CFLibDbKeyHash256 CTenantId,
		String PublishURI );

	/**
	 *	Get the CFBamSchemaDefObj instance for the unique PubURIIdx key.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaDefObj refreshed instance for the unique PubURIIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaDefObj readSchemaDefByPubURIIdx(CFLibDbKeyHash256 CTenantId,
		String PublishURI,
		boolean forceRead );

	ICFBamSchemaDefObj readCachedSchemaDefByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamSchemaDefObj> readCachedSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamSchemaDefObj> readCachedSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId );

	List<ICFBamSchemaDefObj> readCachedSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId );

	ICFBamSchemaDefObj readCachedSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name );

	List<ICFBamSchemaDefObj> readCachedSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	List<ICFBamSchemaDefObj> readCachedSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	ICFBamSchemaDefObj readCachedSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI );

	void deepDisposeSchemaDefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId );

	void deepDisposeSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId );

	void deepDisposeSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name );

	void deepDisposeSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	void deepDisposeSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	void deepDisposeSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaDefObj updateSchemaDef( ICFBamSchemaDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSchemaDef( ICFBamSchemaDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId );

	/**
	 *	Internal use only.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByUNameIdx(CFLibDbKeyHash256 MinorVersionId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail );

	/**
	 *	Internal use only.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL );

	/**
	 *	Internal use only.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 */
	void deleteSchemaDefByPubURIIdx(CFLibDbKeyHash256 CTenantId,
		String PublishURI );
}

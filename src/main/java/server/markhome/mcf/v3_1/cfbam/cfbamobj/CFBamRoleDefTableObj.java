// Description: Java 25 Table Object implementation for RoleDef.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public class CFBamRoleDefTableObj
	implements ICFBamRoleDefTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamRoleDef.CLASS_CODE;
	protected static final int backingClassCode = ICFBamRoleDef.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamRoleDefObj> members;
	private Map<CFLibDbKeyHash256, ICFBamRoleDefObj> allRoleDef;
	private Map< ICFBamRoleDefByUNameIdxKey,
		ICFBamRoleDefObj > indexByUNameIdx;
	private Map< ICFBamRoleDefByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRoleDefObj > > indexByScopeIdx;
	private Map< ICFBamRoleDefByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRoleDefObj > > indexByDefSchemaIdx;
	private Map< ICFBamRoleDefByUDefIdxKey,
		ICFBamRoleDefObj > indexByUDefIdx;
	public static String TABLE_NAME = "RoleDef";
	public static String TABLE_DBNAME = "roledef";

	public CFBamRoleDefTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRoleDefObj>();
		allRoleDef = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
	}

	public CFBamRoleDefTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRoleDefObj>();
		allRoleDef = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamRoleDefTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamRoleDefTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allRoleDef = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		List<ICFBamRoleDefObj> toForget = new LinkedList<ICFBamRoleDefObj>();
		ICFBamRoleDefObj cur = null;
		Iterator<ICFBamRoleDefObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamRoleDefObj.
	 */
	@Override
	public ICFBamRoleDefObj newInstance() {
		ICFBamRoleDefObj inst = new CFBamRoleDefObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamRoleDefObj.
	 */
	@Override
	public ICFBamRoleDefEditObj newEditInstance( ICFBamRoleDefObj orig ) {
		ICFBamRoleDefEditObj edit = new CFBamRoleDefEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamRoleDefObj constructByClassCode( int backingClassCode ) {
		ICFBamRoleDefObj obj = null;
		if( backingClassCode == ICFBamRoleDef.CLASS_CODE) {
			obj = ((ICFBamSchemaObj)schema).getRoleDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamSchemaRole.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getSchemaRoleTableObj().newInstance();
		}
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj realiseRoleDef( ICFBamRoleDefObj Obj ) {
		ICFBamRoleDefObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRoleDefObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamRoleDefObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.remove( keepObj.getPKey() );
					if( mapScopeIdx.size() <= 0 ) {
						indexByScopeIdx.remove( keyScopeIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.remove( keyUDefIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( allRoleDef != null ) {
				allRoleDef.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allRoleDef != null ) {
				allRoleDef.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRoleDefObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamRoleDefObj createRoleDef( ICFBamRoleDefObj Obj ) {
		ICFBamRoleDefObj obj = Obj;
		ICFBamRoleDef rec = obj.getRoleDefRec();
		schema.getCFBamBackingStore().getTableRoleDef().createRoleDef(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamRoleDefObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj readRoleDef( CFLibDbKeyHash256 pkey ) {
		return( readRoleDef( pkey, false ) );
	}

	@Override
	public ICFBamRoleDefObj readRoleDef( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamRoleDefObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamRoleDef readRec = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamRoleDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj readCachedRoleDef( CFLibDbKeyHash256 pkey ) {
		ICFBamRoleDefObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeRoleDef( ICFBamRoleDefObj obj )
	{
		final String S_ProcName = "CFBamRoleDefTableObj.reallyDeepDisposeRoleDef() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRoleDefObj existing = readCachedRoleDef( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamRoleDefByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		keyUNameIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamRoleDefByScopeIdxKey keyScopeIdx = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		keyScopeIdx.setRequiredScopeId( existing.getRequiredScopeId() );

		ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamRoleDefByUDefIdxKey keyUDefIdx = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		keyUDefIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyUDefIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );
		keyUDefIdx.setRequiredName( existing.getRequiredName() );



		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByScopeIdx != null ) {
			if( indexByScopeIdx.containsKey( keyScopeIdx ) ) {
				indexByScopeIdx.get( keyScopeIdx ).remove( pkey );
				if( indexByScopeIdx.get( keyScopeIdx ).size() <= 0 ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByUDefIdx != null ) {
			indexByUDefIdx.remove( keyUDefIdx );
		}


	}
	@Override
	public void deepDisposeRoleDef( CFLibDbKeyHash256 pkey ) {
		ICFBamRoleDefObj obj = readCachedRoleDef( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamRoleDefObj lockRoleDef( CFLibDbKeyHash256 pkey ) {
		ICFBamRoleDefObj locked = null;
		ICFBamRoleDef lockRec = schema.getCFBamBackingStore().getTableRoleDef().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamRoleDefObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockRoleDef", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamRoleDefObj> readAllRoleDef() {
		return( readAllRoleDef( false ) );
	}

	@Override
	public List<ICFBamRoleDefObj> readAllRoleDef( boolean forceRead ) {
		final String S_ProcName = "readAllRoleDef";
		if( ( allRoleDef == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamRoleDefObj> map = new HashMap<CFLibDbKeyHash256,ICFBamRoleDefObj>();
			allRoleDef = map;
			ICFBamRoleDef[] recList = schema.getCFBamBackingStore().getTableRoleDef().readAllDerived( null );
			ICFBamRoleDef rec;
			ICFBamRoleDefObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRoleDefObj realised = (ICFBamRoleDefObj)obj.realise();
			}
		}
		int len = allRoleDef.size();
		ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
		Iterator<ICFBamRoleDefObj> valIter = allRoleDef.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			@Override
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRoleDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRoleDefObj> readCachedAllRoleDef() {
		final String S_ProcName = "readCachedAllRoleDef";
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>();
		if( allRoleDef != null ) {
			int len = allRoleDef.size();
			ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
			Iterator<ICFBamRoleDefObj> valIter = allRoleDef.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readRoleDefByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamRoleDefObj obj = readRoleDef( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readRoleDefByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRoleDefByUNameIdxKey,
				ICFBamRoleDefObj >();
		}
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamRoleDefObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamRoleDef rec = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamRoleDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamRoleDefObj> readRoleDefByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readRoleDefByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamRoleDefObj> readRoleDefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readRoleDefByScopeIdx";
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamRoleDefByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRoleDefObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRoleDefObj>();
			ICFBamRoleDefObj obj;
			ICFBamRoleDef[] recList = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamRoleDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRoleDefObj realised = (ICFBamRoleDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
		Iterator<ICFBamRoleDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			@Override
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRoleDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRoleDefObj> readRoleDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readRoleDefByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamRoleDefObj> readRoleDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readRoleDefByDefSchemaIdx";
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRoleDefByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRoleDefObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRoleDefObj>();
			ICFBamRoleDefObj obj;
			ICFBamRoleDef[] recList = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamRoleDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRoleDefObj realised = (ICFBamRoleDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
		Iterator<ICFBamRoleDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			@Override
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRoleDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		return( readRoleDefByUDefIdx( ScopeId,
			DefSchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamRoleDefObj readRoleDefByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamRoleDefByUDefIdxKey,
				ICFBamRoleDefObj >();
		}
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamRoleDefObj obj = null;
		if( ( ! forceRead ) && indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
		}
		else {
			ICFBamRoleDef rec = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamRoleDefObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamRoleDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj readCachedRoleDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRoleDefObj obj = null;
		obj = readCachedRoleDef( Id );
		return( obj );
	}

	@Override
	public ICFBamRoleDefObj readCachedRoleDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamRoleDefObj obj = null;
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamRoleDefObj> readCachedRoleDefByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedRoleDefByScopeIdx";
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
				Iterator<ICFBamRoleDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRoleDefObj obj;
			Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			@Override
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRoleDefObj> readCachedRoleDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedRoleDefByDefSchemaIdx";
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamRoleDefObj> arrayList = new ArrayList<ICFBamRoleDefObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamRoleDefObj arr[] = new ICFBamRoleDefObj[len];
				Iterator<ICFBamRoleDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRoleDefObj obj;
			Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRoleDefObj> cmp = new Comparator<ICFBamRoleDefObj>() {
			@Override
			public int compare( ICFBamRoleDefObj lhs, ICFBamRoleDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamRoleDefObj readCachedRoleDefByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamRoleDefObj obj = null;
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		if( indexByUDefIdx != null ) {
			if( indexByUDefIdx.containsKey( key ) ) {
				obj = indexByUDefIdx.get( key );
			}
			else {
				Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamRoleDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeRoleDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRoleDefObj obj = readCachedRoleDefByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRoleDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamRoleDefObj obj = readCachedRoleDefByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRoleDefByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeRoleDefByScopeIdx";
		ICFBamRoleDefObj obj;
		List<ICFBamRoleDefObj> arrayList = readCachedRoleDefByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamRoleDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRoleDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeRoleDefByDefSchemaIdx";
		ICFBamRoleDefObj obj;
		List<ICFBamRoleDefObj> arrayList = readCachedRoleDefByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamRoleDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRoleDefByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamRoleDefObj obj = readCachedRoleDefByUDefIdx( ScopeId,
				DefSchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamRoleDefObj updateRoleDef( ICFBamRoleDefObj Obj ) {
		ICFBamRoleDefObj obj = Obj;
		schema.getCFBamBackingStore().getTableRoleDef().updateRoleDef( null,
			Obj.getRoleDefRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getRoleDefTableObj().getClassCode() ) {
			obj = (ICFBamRoleDefObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteRoleDef( ICFBamRoleDefObj Obj ) {
		ICFBamRoleDefObj obj = Obj;
		schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDef( null,
			obj.getRoleDefRec() );
		Obj.forget();
	}

	@Override
	public void deleteRoleDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRoleDefObj obj = readRoleDef(Id);
		if( obj != null ) {
			ICFBamRoleDefEditObj editObj = (ICFBamRoleDefEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamRoleDefEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeRoleDefByIdIdx( Id );
	}

	@Override
	public void deleteRoleDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRoleDefByUNameIdxKey,
				ICFBamRoleDefObj >();
		}
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamRoleDefObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeRoleDefByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteRoleDefByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamRoleDefByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRoleDefObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamRoleDefObj> iter = dict.values().iterator();
			ICFBamRoleDefObj obj;
			List<ICFBamRoleDefObj> toForget = new LinkedList<ICFBamRoleDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByScopeIdx( null,
				ScopeId );
		}
		deepDisposeRoleDefByScopeIdx( ScopeId );
	}

	@Override
	public void deleteRoleDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRoleDefByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRoleDefObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRoleDefObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamRoleDefObj> iter = dict.values().iterator();
			ICFBamRoleDefObj obj;
			List<ICFBamRoleDefObj> toForget = new LinkedList<ICFBamRoleDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeRoleDefByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteRoleDefByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamRoleDefByUDefIdxKey,
				ICFBamRoleDefObj >();
		}
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamRoleDefObj obj = null;
		if( indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableRoleDef().deleteRoleDefByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
		}
		deepDisposeRoleDefByUDefIdx( ScopeId,
				DefSchemaId,
				Name );
	}
}
// Description: Java 25 Table Object implementation for ServerMethod.

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

public class CFBamServerMethodTableObj
	implements ICFBamServerMethodTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamServerMethod.CLASS_CODE;
	protected static final int backingClassCode = ICFBamServerMethod.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamServerMethodObj> members;
	private Map<CFLibDbKeyHash256, ICFBamServerMethodObj> allServerMethod;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj > > indexByTenantIdx;
	private Map< ICFBamServerMethodByUNameIdxKey,
		ICFBamServerMethodObj > indexByUNameIdx;
	private Map< ICFBamServerMethodByMethTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj > > indexByMethTableIdx;
	private Map< ICFBamServerMethodByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj > > indexByDefSchemaIdx;
	public static String TABLE_NAME = "ServerMethod";
	public static String TABLE_DBNAME = "srvmeth";

	public CFBamServerMethodTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerMethodObj>();
		allServerMethod = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
	}

	public CFBamServerMethodTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerMethodObj>();
		allServerMethod = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamServerMethodTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamServerMethodTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allServerMethod = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		List<ICFBamServerMethodObj> toForget = new LinkedList<ICFBamServerMethodObj>();
		ICFBamServerMethodObj cur = null;
		Iterator<ICFBamServerMethodObj> iter = members.values().iterator();
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
	 *	CFBamServerMethodObj.
	 */
	@Override
	public ICFBamServerMethodObj newInstance() {
		ICFBamServerMethodObj inst = new CFBamServerMethodObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamServerMethodObj.
	 */
	@Override
	public ICFBamServerMethodEditObj newEditInstance( ICFBamServerMethodObj orig ) {
		ICFBamServerMethodEditObj edit = new CFBamServerMethodEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamServerMethodObj realiseServerMethod( ICFBamServerMethodObj Obj ) {
		ICFBamServerMethodObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerMethodObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamServerMethodObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.remove( keepObj.getPKey() );
					if( mapMethTableIdx.size() <= 0 ) {
						indexByMethTableIdx.remove( keyMethTableIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamServerMethodObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allServerMethod != null ) {
				allServerMethod.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamServerMethodObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allServerMethod != null ) {
				allServerMethod.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerMethodObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamServerMethodObj createServerMethod( ICFBamServerMethodObj Obj ) {
		ICFBamServerMethodObj obj = Obj;
		ICFBamServerMethod rec = obj.getServerMethodRec();
		schema.getCFBamBackingStore().getTableServerMethod().createServerMethod(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamServerMethodObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamServerMethodObj readServerMethod( CFLibDbKeyHash256 pkey ) {
		return( readServerMethod( pkey, false ) );
	}

	@Override
	public ICFBamServerMethodObj readServerMethod( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamServerMethodObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamServerMethod readRec = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamServerMethodObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamServerMethodObj readCachedServerMethod( CFLibDbKeyHash256 pkey ) {
		ICFBamServerMethodObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeServerMethod( ICFBamServerMethodObj obj )
	{
		final String S_ProcName = "CFBamServerMethodTableObj.reallyDeepDisposeServerMethod() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerMethodObj existing = readCachedServerMethod( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamServerMethodByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamServerMethodByMethTableIdxKey keyMethTableIdx = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		keyMethTableIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );


		schema.getParamTableObj().deepDisposeParamByServerMethodIdx( existing.getRequiredId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByMethTableIdx != null ) {
			if( indexByMethTableIdx.containsKey( keyMethTableIdx ) ) {
				indexByMethTableIdx.get( keyMethTableIdx ).remove( pkey );
				if( indexByMethTableIdx.get( keyMethTableIdx ).size() <= 0 ) {
					indexByMethTableIdx.remove( keyMethTableIdx );
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


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeServerMethod( CFLibDbKeyHash256 pkey ) {
		ICFBamServerMethodObj obj = readCachedServerMethod( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamServerMethodObj lockServerMethod( CFLibDbKeyHash256 pkey ) {
		ICFBamServerMethodObj locked = null;
		ICFBamServerMethod lockRec = schema.getCFBamBackingStore().getTableServerMethod().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamServerMethodObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockServerMethod", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamServerMethodObj> readAllServerMethod() {
		return( readAllServerMethod( false ) );
	}

	@Override
	public List<ICFBamServerMethodObj> readAllServerMethod( boolean forceRead ) {
		final String S_ProcName = "readAllServerMethod";
		if( ( allServerMethod == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> map = new HashMap<CFLibDbKeyHash256,ICFBamServerMethodObj>();
			allServerMethod = map;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readAllDerived( null );
			ICFBamServerMethod rec;
			ICFBamServerMethodObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerMethodObj realised = (ICFBamServerMethodObj)obj.realise();
			}
		}
		int len = allServerMethod.size();
		ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
		Iterator<ICFBamServerMethodObj> valIter = allServerMethod.values().iterator();
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
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
		List<ICFBamServerMethodObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerMethodObj> readCachedAllServerMethod() {
		final String S_ProcName = "readCachedAllServerMethod";
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>();
		if( allServerMethod != null ) {
			int len = allServerMethod.size();
			ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
			Iterator<ICFBamServerMethodObj> valIter = allServerMethod.values().iterator();
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
		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
	public ICFBamServerMethodObj readServerMethodByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readServerMethodByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamServerMethodObj readServerMethodByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamServerMethodObj obj = readServerMethod( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readServerMethodByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerMethodByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerMethodObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerMethodObj realised = (ICFBamServerMethodObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
		Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
		List<ICFBamServerMethodObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerMethodObj readServerMethodByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readServerMethodByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamServerMethodObj readServerMethodByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerMethodObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerMethodObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamServerMethod rec = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamServerMethodObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readServerMethodByMethTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerMethodByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( ( ! forceRead ) && indexByMethTableIdx.containsKey( key ) ) {
			dict = indexByMethTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerMethodObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByMethTableIdx( null,
				TableId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerMethodObj realised = (ICFBamServerMethodObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByMethTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
		Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
		List<ICFBamServerMethodObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readServerMethodByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamServerMethodObj> readServerMethodByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerMethodByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerMethodObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerMethodObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerMethodObj realised = (ICFBamServerMethodObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
		Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
		List<ICFBamServerMethodObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerMethodObj readCachedServerMethodByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerMethodObj obj = null;
		obj = readCachedServerMethod( Id );
		return( obj );
	}

	@Override
	public List<ICFBamServerMethodObj> readCachedServerMethodByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedServerMethodByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
				Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
			ICFBamServerMethodObj obj;
			Iterator<ICFBamServerMethodObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
	public ICFBamServerMethodObj readCachedServerMethodByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerMethodObj obj = null;
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamServerMethodObj> valIter = members.values().iterator();
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
			Iterator<ICFBamServerMethodObj> valIter = members.values().iterator();
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
	public List<ICFBamServerMethodObj> readCachedServerMethodByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedServerMethodByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>();
		if( indexByMethTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
			if( indexByMethTableIdx.containsKey( key ) ) {
				dict = indexByMethTableIdx.get( key );
				int len = dict.size();
				ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
				Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
			ICFBamServerMethodObj obj;
			Iterator<ICFBamServerMethodObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
	public List<ICFBamServerMethodObj> readCachedServerMethodByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedServerMethodByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamServerMethodObj> arrayList = new ArrayList<ICFBamServerMethodObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamServerMethodObj arr[] = new ICFBamServerMethodObj[len];
				Iterator<ICFBamServerMethodObj> valIter = dict.values().iterator();
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
			ICFBamServerMethodObj obj;
			Iterator<ICFBamServerMethodObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerMethodObj> cmp = new Comparator<ICFBamServerMethodObj>() {
			@Override
			public int compare( ICFBamServerMethodObj lhs, ICFBamServerMethodObj rhs ) {
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
	public void deepDisposeServerMethodByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerMethodObj obj = readCachedServerMethodByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerMethodByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeServerMethodByTenantIdx";
		ICFBamServerMethodObj obj;
		List<ICFBamServerMethodObj> arrayList = readCachedServerMethodByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamServerMethodObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerMethodByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerMethodObj obj = readCachedServerMethodByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerMethodByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeServerMethodByMethTableIdx";
		ICFBamServerMethodObj obj;
		List<ICFBamServerMethodObj> arrayList = readCachedServerMethodByMethTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerMethodObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerMethodByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeServerMethodByDefSchemaIdx";
		ICFBamServerMethodObj obj;
		List<ICFBamServerMethodObj> arrayList = readCachedServerMethodByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamServerMethodObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamServerMethodObj updateServerMethod( ICFBamServerMethodObj Obj ) {
		ICFBamServerMethodObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerMethod().updateServerMethod( null,
			Obj.getServerMethodRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().getClassCode() ) {
			obj = (ICFBamServerMethodObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteServerMethod( ICFBamServerMethodObj Obj ) {
		ICFBamServerMethodObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethod( null,
			obj.getServerMethodRec() );
		Obj.forget();
	}

	@Override
	public void deleteServerMethodByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerMethodObj obj = readServerMethod(Id);
		if( obj != null ) {
			ICFBamServerMethodEditObj editObj = (ICFBamServerMethodEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamServerMethodEditObj)obj.beginEdit();
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
		deepDisposeServerMethodByIdIdx( Id );
	}

	@Override
	public void deleteServerMethodByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByTenantIdx( null,
				TenantId );
			Iterator<ICFBamServerMethodObj> iter = dict.values().iterator();
			ICFBamServerMethodObj obj;
			List<ICFBamServerMethodObj> toForget = new LinkedList<ICFBamServerMethodObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByTenantIdx( null,
				TenantId );
		}
		deepDisposeServerMethodByTenantIdx( TenantId );
	}

	@Override
	public void deleteServerMethodByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerMethodObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerMethodObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeServerMethodByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteServerMethodByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( indexByMethTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict = indexByMethTableIdx.get( key );
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByMethTableIdx( null,
				TableId );
			Iterator<ICFBamServerMethodObj> iter = dict.values().iterator();
			ICFBamServerMethodObj obj;
			List<ICFBamServerMethodObj> toForget = new LinkedList<ICFBamServerMethodObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByMethTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByMethTableIdx( null,
				TableId );
		}
		deepDisposeServerMethodByMethTableIdx( TableId );
	}

	@Override
	public void deleteServerMethodByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerMethodObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerMethodObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamServerMethodObj> iter = dict.values().iterator();
			ICFBamServerMethodObj obj;
			List<ICFBamServerMethodObj> toForget = new LinkedList<ICFBamServerMethodObj>();
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
			schema.getCFBamBackingStore().getTableServerMethod().deleteServerMethodByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeServerMethodByDefSchemaIdx( DefSchemaId );
	}
}